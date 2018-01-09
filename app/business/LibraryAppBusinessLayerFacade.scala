package business

import database.DbRepository
import scala.concurrent.{ Future, ExecutionContext }
import javax.inject._
import scala.collection.immutable._
import java.time.LocalDate

/**
 * This facade defines all the business operations that can be performed by the business API.  The business layer does all business-level validation of
 * requests, and makes changes to permanent storage as necessary through the repository object, which is provided through injection.  The business API
 * uses the UnitOfWork pattern exposed by the repository to define complex operations and perform them as a single, atomic operation, thus ensuring
 * data integrity even if concurrent operations to the repository are made.  Business operations are performed asynchronously, and thus always return
 * `Future` objects, which can be used to schedule callbacks to retrieve the operation's result when finished.
 */
class LibraryAppBusinessLayerFacade @Inject()(repository: DbRepository) {
  def registerPublisher(name : String) : Future[PublisherID] =
    repository.createPublisher(name).execute()
    
  def registerBook(book : EditBookDTO) : Future[BookID] =
    repository.getBookByCallNumber(book.callNumber).flatMap {
      case Some(_) =>
        throw new BusinessException(s"A book with that call number already exists.  If you are adding a second copy of the book to the system, please use '${book.callNumber}B' instead.")
      case None =>
        repository.createBook(book)
    }.execute()
  
  def getPublishers() : Future[Seq[Publisher]] =
    repository.getPublishers().execute()
    
  def getBook(bookID : BookID) : Future[Book] =
    repository.getBook(bookID).execute()
  
  def editBook(bookID : BookID, book : EditBookDTO) : Future[Unit] =
    repository.getBookByCallNumber(book.callNumber).flatMap { otherBook =>
      if(otherBook.map(_.id).getOrElse(bookID) != bookID)
        throw new BusinessException(s"A book with that call number already exists.  If you are adding a second copy of the book to the system, please use '${book.callNumber}B' instead.")
      else
        repository.editBook(bookID, book)
    }.execute()
  
  def registerLibraryMember(libraryMember : EditLibraryMemberDTO) : Future[LibraryMemberID] =
    repository.createLibraryMember(libraryMember).execute()
    
  def searchBooks(searchTerms : Seq[String]) : Future[Seq[BookListing]] =
    repository.searchBooks(searchTerms).execute()
    
  def getLibraryMembers() : Future[Seq[LibraryMember]] =
    repository.getLibraryMembers().execute()
    
  def reportBookLost(bookID : BookID) : Future[Unit] =
    repository.getBookStatus(bookID).flatMap { bookStatus =>
      if(bookStatus == BookStatus.Disposed)
        throw new BusinessException("Book is disposed.")
      repository.setBookStatus(bookID, BookStatus.Lost)
    }.execute()
    
  def reportBookFound(bookID : BookID) : Future[Unit] =
    repository.getBookStatus(bookID).flatMap { bookStatus =>
      if(bookStatus != BookStatus.Lost)
        throw new BusinessException("Book isn't lost.")
      repository.getBookLoans(bookID).flatMap { loans =>
        if(loans.isEmpty || !loans.head.returnedDate.isEmpty)
          repository.setBookStatus(bookID, BookStatus.Available)
        else
          repository.setBookStatus(bookID, BookStatus.CheckedOut)
      }
    }.execute()
    
  def disposeBook(bookID : BookID) : Future[Unit] =
    repository.getBookStatus(bookID).flatMap { bookStatus =>
      if(bookStatus != BookStatus.Available)
        throw new BusinessException("Book must be available in order to be disposed.")
      repository.setBookStatus(bookID, BookStatus.Disposed)
    }.execute()
    
  def loanBook(bookID : BookID, loan : LoanBookDTO) (implicit executor : scala.concurrent.ExecutionContext) : Future[BookLoanID] =
    if(loan.loanedDate.isAfter(loan.dueDate))
      Future.failed(new BusinessException("Due date must be on or after loaned date."))
    else
      repository.getLibraryMember(loan.memberID).flatMap { member =>
        if(member.joinedDate.isAfter(loan.loanedDate))
            throw new BusinessException(s"The loaned date must be on or after the member's joined date, ${member.joinedDate}.")
        repository.getLibraryMemberLoans(loan.memberID).flatMap { memberLoans =>
          if(memberLoans.filter(_.returnedDate == None).size >= 5)
            throw new BusinessException("Member may not have more than 5 open book loans at a time.")
          repository.getBookStatus(bookID).flatMap { bookStatus =>
            if(bookStatus != BookStatus.Available)
              throw new BusinessException("Book is not availabled to be loaned.")
            repository.getBookLoans(bookID).flatMap { loans =>
              if(!loans.isEmpty && loans.head.returnedDate.get.isAfter(loan.loanedDate))
                throw new BusinessException(s"The loaned date must be on or after the book's last returned date, ${loans.head.returnedDate.get}.")
              repository.createBookLoan(bookID, loan).flatMap{ loanID =>
                repository.setBookStatus(bookID, BookStatus.CheckedOut).map(_ => loanID)
              }
            }
          }
        }
      }.execute()
    
  def reportBookReturned(bookID : BookID, returnedDate : LocalDate)  (implicit executor : scala.concurrent.ExecutionContext) : Future[Unit] =
    repository.getBookLoans(bookID).flatMap { loans =>
      if(loans.isEmpty)
        throw new BusinessException("The book is not loaned out.")
      if(loans.head.loanedDate.isAfter(returnedDate))
        throw new BusinessException("The returned date must on or after the loaned date.")
      repository.setReturnedDate(loans.head.id, returnedDate).flatMap { _ =>
        repository.setBookStatus(bookID, BookStatus.Available)
      }
    }.execute()
    
  def getBookLoanHistory(bookID : BookID) : Future[Seq[BookLoan]] =
    repository.getBookLoans(bookID).execute()
  
  def getAuthors() : Future[Seq[Author]] =
    repository.getAuthors().execute()
  
  def createAuthor(name : PersonName) : Future[AuthorID] =
    repository.createAuthor(name).execute()
}