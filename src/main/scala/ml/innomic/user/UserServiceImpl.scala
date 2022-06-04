package ml.innomic.user

import ml.innomic.PostgresContext

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class UserServiceImpl()(implicit
    executionContext: ExecutionContext,
) extends UserService {

  import PostgresContext._

  override def findAll(query: UserQuery): Future[Seq[User]] = Future {
    val q = quote {
      infix"SELECT 1".as[Int]
    }

    val _ = PostgresContext.run(q)
    ???
  }

  override def findOne(id: String): Future[Option[User]] = Future {
    None
  }

  override def create(entity: User): Future[User] = Future {
    quote {
      lift(entity)
    }
    ???
  }

  override def update(id: String, entity: User): Future[User] = Future {
    entity
  }

  override def remove(id: String): Future[Unit] = Future {
    ()
  }
}
