package ml.innomic.machine

import ml.innomic.CassandraContext

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class MachineServiceImpl()(implicit
    executionContext: ExecutionContext
) extends MachineService {

  import CassandraContext._

  override def findAll(query: MachineQuery): Future[Seq[Machine]] = Future {
    Seq.empty
  }

  override def findOne(id: String): Future[Option[Machine]] = Future {
    Some(
      Machine(id = Some(id), name = "Hello, World!")
    )
  }

  override def create(entity: Machine): Future[Machine] = Future {
    quote {
      lift(entity)
    }
    ???
  }

  override def update(id: String, entity: Machine): Future[Machine] = Future {
    entity
  }

  override def remove(id: String): Future[Unit] = Future {
    ()
  }
}
