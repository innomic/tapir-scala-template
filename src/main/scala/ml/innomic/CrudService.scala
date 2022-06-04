package ml.innomic

trait CrudService[F[_], E, ID, Q] {
  def findAll(query: Q): F[Seq[E]]
  def findOne(id: ID): F[Option[E]]
  def create(entity: E): F[E]
  def update(id: ID, entity: E): F[E]
  def remove(id: ID): F[Unit]
}
