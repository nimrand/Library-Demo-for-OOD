package business

import scala.concurrent._

trait UnitOfWork[+T, Z[X] <: UnitOfWork[X, Z]] {
  def map[U](f : T => U) : Z[U]
  def flatMap[U](f : T => Z[U]) : Z[U]
  def execute() : Future[T]
}