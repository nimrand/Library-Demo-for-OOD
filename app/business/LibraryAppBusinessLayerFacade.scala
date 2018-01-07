package business

import db.SlickRepository
import scala.concurrent.{ Future, ExecutionContext }
import javax.inject._

class LibraryAppBusinessLayerFacade @Inject()(repository: SlickRepository) {
  def registerPublisher(publisher : String) : Future[Publisher] = repository.createPublisher(publisher)
}