package ml.innomic.user

import ml.innomic.CrudService

import scala.concurrent.Future

trait UserService extends CrudService[Future, User, String, UserQuery] {}
