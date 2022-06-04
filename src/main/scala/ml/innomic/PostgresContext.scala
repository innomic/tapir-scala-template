package ml.innomic

import io.getquill.Literal
import io.getquill.PostgresJdbcContext

object PostgresContext extends PostgresJdbcContext(Literal, "postgres")
