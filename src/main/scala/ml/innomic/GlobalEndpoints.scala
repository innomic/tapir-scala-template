package ml.innomic

import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.generic.auto._
import io.circe.generic.auto._

object GlobalEndpoints {
  val global: PublicEndpoint[Unit, ErrorInfo, Unit, Any] =
    endpoint.errorOut(jsonBody[ErrorInfo])

}
