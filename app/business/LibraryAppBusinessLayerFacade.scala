package business

import database.DbRepository
import scala.concurrent.{ Future, ExecutionContext }
import javax.inject._
import scala.collection.immutable._

class LibraryAppBusinessLayerFacade @Inject()(repository: DbRepository) {
  def registerPublisher(publisher : String) : Future[PublisherID] = repository.createPublisher(publisher)
  def registerBook(book : EditBookDTO) : Future[BookID] = repository.createBook(book)
  def getPublishers() : Future[Seq[Publisher]] = repository.getPublishers()
  def getBook(bookID : BookID) : Future[Book] = repository.retrieveBook(bookID)
  def editBook(bookID : BookID, book : EditBookDTO) : Future[Unit] = repository.editBook(bookID, book)
  def registerLibraryMember(libraryMember : EditLibraryMemberDTO) : Future[LibraryMemberID] = repository.createLibraryMember(libraryMember)
  def searchBooks(searchTerms : Seq[String]) : Future[Seq[BookListing]] = repository.searchBooks(searchTerms)
  def getLibraryMembers() : Future[Seq[LibraryMember]] =
    repository.getLibraryMembers()
  def reportBookLost(bookID : BookID) : Future[Unit] =
    repository.setBookStatus(bookID, BookStatus.Lost)
  def reportBookFound(bookID : BookID) : Future[Unit] =
    repository.setBookStatus(bookID, BookStatus.Available)
  def disposeBook(bookID : BookID) : Future[Unit] =
    repository.setBookStatus(bookID, BookStatus.Disposed)
  def loanBook(bookID : BookID, loan : LoanBookDTO) (implicit executor : scala.concurrent.ExecutionContext) : Future[BookLoanID] =
    repository.createBookLoan(bookID, loan).flatMap{ loanID => {
      repository.setBookStatus(bookID, BookStatus.CheckedOut).map(_ => loanID)
    }}
}