package business

import scala.concurrent.{ Future, ExecutionContext }

trait Repository {
  def createPublisher(name : String) : Future[Publisher]
}