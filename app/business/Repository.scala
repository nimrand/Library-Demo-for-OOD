package business

import scala.collection.immutable._
import java.time.LocalDate
import com.google.inject.ImplementedBy

@ImplementedBy(classOf[database.DbRepository])
trait Repository {
  type W[T] <: UnitOfWork[T, W]
  
  def createAuthor(name : PersonName) : W[AuthorID]
  def createBook(book : EditBookDTO) : W[BookID]
  def createBookLoan(bookID : BookID, loan : LoanBookDTO) : W[BookLoanID]
  def createLibraryMember(libraryMember : EditLibraryMemberDTO) : W[LibraryMemberID]
  def createPublisher(name : String) : W[PublisherID]
  def editBook(bookID : BookID, book : EditBookDTO) : W[Unit]
  def getAuthors() : W[Seq[Author]]
  def getBook(bookID : BookID) : W[Book]
  def getBookByCallNumber(callNumber : CallNumber) : W[Option[Book]]
  def getBookLoans(bookID : BookID) : W[Seq[BookLoan]]
  def getBookStatus(bookID : BookID) : W[BookStatus]
  def getLibraryMember(memberID : LibraryMemberID) : W[LibraryMember]
  def getLibraryMemberLoans(libraryMemberID : LibraryMemberID) : W[Seq[BookLoan]]
  def getLibraryMembers() : W[Seq[LibraryMember]]
  def getPublishers() : W[Seq[Publisher]]
  def searchBooks(query : AdvancedBookSearchQuery, sort : BookSearchSort) : W[Seq[SearchResult[BookListing]]]
  def searchBooks(searchTerms : Seq[String], sort : BookSearchSort) : W[Seq[SearchResult[BookListing]]]
  def setBookStatus(bookID : BookID, status : BookStatus) : W[Unit]
  def setReturnedDate(bookLoanID : BookLoanID, returnedDate : LocalDate) : W[Unit]
}