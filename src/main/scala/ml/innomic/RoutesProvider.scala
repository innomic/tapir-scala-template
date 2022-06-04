package ml.innomic

import ml.innomic.machine.MachineEndpoints
import ml.innomic.machine.MachineService
import ml.innomic.user.UserEndpoints
import ml.innomic.user.UserService
import sttp.tapir.server.ServerEndpoint

import scala.concurrent.Future

class RoutesProvider(
    userService: UserService,
    machineService: MachineService
) {
  private val user = List(
    UserEndpoints.create.serverLogicSuccess(userService.create),
    UserEndpoints.remove.serverLogicSuccess(userService.remove),
    UserEndpoints.update.serverLogicSuccess((userService.update _).tupled),
    UserEndpoints.findAll.serverLogicSuccess(userService.findAll),
    UserEndpoints.findOne.serverLogicSuccess(userService.findOne),
  )

  private val machine = List(
    MachineEndpoints.findOne.serverLogicSuccess(machineService.findOne),
  )

  val routes: List[ServerEndpoint[Any, Future]] = user ++ machine
}
