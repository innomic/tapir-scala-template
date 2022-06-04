package ml.innomic

import io.getquill.CassandraAsyncContext
import io.getquill.Literal

object CassandraContext extends CassandraAsyncContext(Literal, "cassandra")
