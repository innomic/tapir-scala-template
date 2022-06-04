package ml.innomic.machine

import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._
import io.circe.generic.auto._
import ml.innomic.ErrorInfo
import ml.innomic.GlobalEndpoints

object MachineEndpoints {
  private val baseEndpoint: PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
    GlobalEndpoints.global
      .tag("machine")
      .in("machine")

  val findAll: PublicEndpoint[MachineQuery, ErrorInfo, Seq[Machine], Any] =
    baseEndpoint.get
      .in(query[Option[String]]("name").mapTo[MachineQuery])
      .out(jsonBody[Seq[Machine]])

  val findOne: PublicEndpoint[String, ErrorInfo, Option[Machine], Any] =
    baseEndpoint.get
      .in(path[String]("id"))
      .out(jsonBody[Option[Machine]])

  val create: PublicEndpoint[Machine, ErrorInfo, Machine, Any] =
    baseEndpoint.post
      .in(jsonBody[Machine])
      .out(jsonBody[Machine])

  val update: PublicEndpoint[(String, Machine), ErrorInfo, Machine, Any] =
    baseEndpoint.patch
      .in(path[String]("id"))
      .in(jsonBody[Machine])
      .out(jsonBody[Machine])

  val remove: PublicEndpoint[String, ErrorInfo, Unit, Any] =
    baseEndpoint.delete
      .in(path[String]("id"))
}
