package business

import database.DbRepository
import scala.concurrent.{ Future, ExecutionContext }
import javax.inject._
import scala.collection.immutable._
import java.time.LocalDate

class LibraryAppBusinessLayerFacade @Inject()(repository: DbRepository) {
  def registerPublisher(name : String) : Future[PublisherID] =
    repository.createPublisher(name).execute()
    
  def registerBook(book : EditBookDTO) : Future[BookID] =
    repository.createBook(book).execute()
  
  def getPublishers() : Future[Seq[Publisher]] =
    repository.getPublishers().execute()
    
  def getBook(bookID : BookID) : Future[Book] =
    repository.retrieveBook(bookID).execute()
  
  def editBook(bookID : BookID, book : EditBookDTO) : Future[Unit] =
    repository.editBook(bookID, book).execute()
  
  def registerLibraryMember(libraryMember : EditLibraryMemberDTO) : Future[LibraryMemberID] =
    repository.createLibraryMember(libraryMember).execute()
    
  def searchBooks(searchTerms : Seq[String]) : Future[Seq[BookListing]] =
    repository.searchBooks(searchTerms).execute()
    
  def getLibraryMembers() : Future[Seq[LibraryMember]] =
    repository.getLibraryMembers().execute()
    
  def reportBookLost(bookID : BookID) : Future[Unit] =
    repository.setBookStatus(bookID, BookStatus.Lost).execute()
    
  def reportBookFound(bookID : BookID) : Future[Unit] =
    repository.setBookStatus(bookID, BookStatus.Available).execute()
    
  def disposeBook(bookID : BookID) : Future[Unit] =
    repository.setBookStatus(bookID, BookStatus.Disposed).execute()
    
  def loanBook(bookID : BookID, loan : LoanBookDTO) (implicit executor : scala.concurrent.ExecutionContext) : Future[BookLoanID] =
    repository.createBookLoan(bookID, loan).flatMap{ loanID =>
      repository.setBookStatus(bookID, BookStatus.CheckedOut).map(_ => loanID)
    }.execute()
    
  def reportBookReturned(bookID : BookID, returnedDate : LocalDate)  (implicit executor : scala.concurrent.ExecutionContext) : Future[Unit] =
    repository.getBookLoans(bookID).flatMap { loans =>
      repository.setReturnedDate(loans.head.id, returnedDate).flatMap { _ =>
        repository.setBookStatus(bookID, BookStatus.Available)
      }
    }.execute()
    
  def getBookLoanHistory(bookID : BookID) : Future[Seq[BookLoan]] =
    repository.getBookLoans(bookID).execute()
}