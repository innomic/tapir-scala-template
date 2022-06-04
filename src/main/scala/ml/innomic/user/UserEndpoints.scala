package ml.innomic.user

import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._
import io.circe.generic.auto._
import ml.innomic.ErrorInfo
import ml.innomic.GlobalEndpoints

object UserEndpoints {
  private val baseEndpoint: PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
    GlobalEndpoints.global
      .tag("user")
      .in("user")

  val findAll: PublicEndpoint[UserQuery, ErrorInfo, Seq[User], Any] =
    baseEndpoint.get
      .in(query[Option[String]]("name").mapTo[UserQuery])
      .out(jsonBody[Seq[User]])

  val findOne: PublicEndpoint[String, ErrorInfo, Option[User], Any] =
    baseEndpoint.get
      .in(path[String]("id"))
      .out(jsonBody[Option[User]])

  val create: PublicEndpoint[User, ErrorInfo, User, Any] =
    baseEndpoint.post
      .in(jsonBody[User])
      .out(jsonBody[User])

  val update: PublicEndpoint[(String, User), ErrorInfo, User, Any] =
    baseEndpoint.patch
      .in(path[String]("id"))
      .in(jsonBody[User])
      .out(jsonBody[User])

  val remove: PublicEndpoint[String, ErrorInfo, Unit, Any] =
    baseEndpoint.delete
      .in(path[String]("id"))
}
