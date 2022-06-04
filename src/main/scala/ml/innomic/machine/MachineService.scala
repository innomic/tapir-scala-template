package ml.innomic.machine

import ml.innomic.CrudService

import scala.concurrent.Future

trait MachineService extends CrudService[Future, Machine, String, MachineQuery] {}
