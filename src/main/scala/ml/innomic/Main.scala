package ml.innomic

import akka.actor.CoordinatedShutdown
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import ml.innomic.machine.MachineService
import ml.innomic.machine.MachineServiceImpl
import ml.innomic.user.UserService
import ml.innomic.user.UserServiceImpl
import org.slf4j.LoggerFactory
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter

import scala.concurrent.Future
import scala.util.Failure
import scala.util.Success

object Main {
  private val logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    ActorSystem[Nothing](
      Behaviors.setup[Nothing] { context =>
        import com.softwaremill.macwire._
        import context.{ executionContext, system }

        lazy val config: Config = ConfigFactory.load()

        lazy val userService: UserService       = wire[UserServiceImpl]
        lazy val machineService: MachineService = wire[MachineServiceImpl]

        lazy val routesProvider: RoutesProvider = wire[RoutesProvider]

        val endpoints = routesProvider.routes

        val docsEndpoints = SwaggerInterpreter()
          .fromServerEndpoints[Future](
            endpoints,
            title = "My App",
            version = "1.0.0",
          )

        val route = AkkaHttpServerInterpreter().toRoute(endpoints ++ docsEndpoints)

        val interface = config.getString("http.host")
        val port      = config.getInt("http.port")

        val binding = Http()
          .newServerAt(interface, port)
          .bindFlow(route)

        binding.onComplete {
          case Success(_) =>
            logger.info(s"Server is listening on http://$interface:$port")
          case Failure(exception) =>
            logger.error(s"Failure : $exception")
            context.system.terminate()
        }

        CoordinatedShutdown(system).addJvmShutdownHook {
          //
        }

        Behaviors.empty
      },
      name = "system"
    )
  }
}
