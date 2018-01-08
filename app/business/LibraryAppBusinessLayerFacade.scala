package business

import db.DbRepository
import scala.concurrent.{ Future, ExecutionContext }
import javax.inject._
import scala.collection.immutable._

class LibraryAppBusinessLayerFacade @Inject()(repository: DbRepository) {
  def registerPublisher(publisher : String) : Future[PublisherID] = repository.createPublisher(publisher)
  def registerBook(book : EditBookDTO) : Future[BookID] = repository.createBook(book)
  def getPublishers() : Future[Seq[Publisher]] = repository.getPublishers()
  def getBook(bookID : BookID) : Future[Book] = repository.retrieveBook(bookID)
  def editBook(bookID : BookID, book : EditBookDTO) : Future[Unit] = repository.editBook(bookID, book)
}