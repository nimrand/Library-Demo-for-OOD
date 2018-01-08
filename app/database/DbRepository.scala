package database

import business._
import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }
import scala.collection.immutable._
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Singleton
class DbRepository @Inject() (dbSchema: DbSchema)(implicit ec: ExecutionContext) extends Repository {
  import dbSchema.dbConfig._
  import profile.api._
  import dbSchema._
  
  implicit val localDateOrdering: Ordering[LocalDate] = Ordering.by(_.toEpochDay)
  
  implicit class RichEditBookDTO(book : EditBookDTO) {
    def fields =
      (book.title, book.isbn.toString, book.price.toString, book.keywords.mkString(" "), book.description, book.callNumber, book.publicationDate.format(dateFormat), book.publisherID.asInt)
  }
  
  implicit class RichPersonName(name : PersonName) {
    def fields =
      (name.firstName, name.middleName, name.lastName, name.suffixName, name.titles.mkString(" "))
  }
  
  def createPublisher(name : String) : Future[PublisherID] = db.run {
    publishers.map(p => p.name) returning dbSchema.publishers.map(_.id) into ((name, id) => PublisherID(id)) += (name)
  }
  
  def getPublishers() : Future[Seq[Publisher]] = db.run {
    publishers.result
  }.map(_.to[Seq])
  
  def createBook(book : EditBookDTO) : Future[BookID] = {
    val fields = (book.title, book.isbn.toString, book.price.toString, book.keywords.mkString(" "), book.description, book.callNumber, book.publicationDate.format(dateFormat), book.publisherID.asInt, 0)
    db.run {
      (books.map(p => (p.title, p.isbn, p.price, p.keywords, p.description, p.callNumber, p.publicationDate, p.publisherID, p.statusCode))
          returning books.map(_.id)
          into ((fields, id) => BookID(id))
      ) += fields
    }
  }
  
  def retrieveBook(bookID : BookID) : Future[Book] = {
    db.run(books.filter(_.id === bookID.asInt).result.head).flatMap{ bookRecord =>
      db.run(publishers.filter(_.id === bookRecord.publisherID.asInt).result.head).map{ publisherRecord =>
        new Book(bookRecord.id, bookRecord.title, bookRecord.isbn, bookRecord.price, bookRecord.keywords, bookRecord.description, bookRecord.callNumber, bookRecord.publicationDate, new Publisher(bookRecord.publisherID, publisherRecord.name), statusCodeToBookStatus(bookRecord.statusCode))
      }
    }
  }
  
  def editBook(bookID : BookID, book : EditBookDTO) : Future[Unit] = {
    val bookIDInt = bookID.asInt
    db.run(books.filter(_.id === bookIDInt).map(p => (p.title, p.isbn, p.price, p.keywords, p.description, p.callNumber, p.publicationDate, p.publisherID)).update(book.fields)).map(_ => ())
  }
  
  def setBookStatus(bookID : BookID, status : BookStatus) : Future[Unit] = {
    val bookIDInt = bookID.asInt
    val statusCode = bookStatusToStatusCode(status)
    db.run(books.filter(_.id === bookIDInt).map(_.statusCode).update(statusCode)).map(_ => ())
  }
  
  def createLibraryMember(libraryMember : EditLibraryMemberDTO) : Future[LibraryMemberID] = {
    db.run{
      (personNames.map(n => (n.firstName, n.middleName, n.lastName, n.suffixName, n.titles))
          returning personNames.map(_.id)
          into ((fields, id) => id)
      ) += libraryMember.name.fields
    }.flatMap(nameID => {
      val memberColumns = (nameID, libraryMember.joinedDate.format(dateFormat))
      db.run{
        (libraryMembers.map(m => (m.nameID, m.joinedDate))
            returning libraryMembers.map(_.id)
            into ((fields, id) => id)
        ) += memberColumns
      }
    }).map(LibraryMemberID(_))
  }
  
  def getLibraryMembers() : Future[Seq[LibraryMember]] =
    db.run {
      (libraryMembers joinLeft personNames on (_.nameID === _.id)).result
    }.map(_.map(fields => new LibraryMember(LibraryMemberID(fields._1._1), fields._2.get._2, fields._1._3)).to[Seq])
  
  private def searchBooksByTerm(searchTerm : String) : Future[Seq[BookListing]] = {
    val wildSearchTerm = s"%$searchTerm%"
    val firstQuery = db.run(books.filter(b => b.title.like(wildSearchTerm) || b.keywords.like(wildSearchTerm) || b.description.like(wildSearchTerm) || b.isbn.like(wildSearchTerm) || b.callNumber.like(wildSearchTerm)).map(_.id).result)
    val secondQuery = db.run(publishers.filter(p => p.name.like(wildSearchTerm)).map(_.id).result).flatMap { publisherIDs =>
      val publisherIDSet = Set(publisherIDs : _*)
      db.run(books.filter(b => b.publisherID.inSet(publisherIDSet)).map(_.id).result)
    }
    Future.sequence(List(firstQuery, secondQuery)).flatMap { bookIDLists =>
      val bookIDs = Set(bookIDLists.flatten : _*)
      db.run(books.filter(_.id.inSet(bookIDs)).result).map(_.to[Seq].map(_.toBookListing))
    }
  }
  
  def searchBooks(searchTerms : Seq[String]) : Future[Seq[BookListing]] = {
    Future.sequence(for(searchTerm <- searchTerms) yield searchBooksByTerm(searchTerm)).map { resultSets =>
      val allBooks = resultSets.flatten.groupBy(_.bookID).mapValues(_.head).values
      val relevance = resultSets.flatten.groupBy(_.bookID).mapValues(_.size)
      allBooks.to[Seq].sortBy(book => relevance(book.bookID) * -1)
    }
  }
  
  def createBookLoan(bookID : BookID, loan : LoanBookDTO) : Future[BookLoanID] = {
    val fields = (bookID.asInt, loan.memberID.asInt, loan.loanedDate.format(dateFormat), loan.dueDate.format(dateFormat))
    db.run{
      (bookLoans.map(l => (l.bookID, l.memberID, l.loanedDate, l.dueDate))
        returning bookLoans.map(_.id)
        into ((name, id) => BookLoanID(id))
      ) += fields
    }
  }
  
  def getBookLoans(bookID : BookID) : Future[Seq[BookLoan]] = {
    val bookIDInt = bookID.asInt
    db.run{ (bookLoans.filter(_.bookID === bookIDInt) join libraryMembers on (_.memberID === _.id) join personNames on (_._2.id === _.id)).result }.map(
        _.to[Seq].map(fields => new BookLoan(BookLoanID(fields._1._1._1), BookID(fields._1._1._2), new LibraryMember(LibraryMemberID(fields._1._1._3), fields._2._2, fields._1._2._3), LocalDate.parse(fields._1._1._4, dateFormat), LocalDate.parse(fields._1._1._5, dateFormat), fields._1._1._6.map(LocalDate.parse(_, dateFormat)))).sortBy(_.loanedDate).reverse
    )
  }
  
  def setReturnedDate(bookLoanID : BookLoanID, returnedDate : LocalDate) : Future[Unit] = {
    val bookLoanIDInt = bookLoanID.asInt
    val returnedDateString = Some(returnedDate.format(dateFormat))
    db.run(bookLoans.filter(_.id === bookLoanIDInt).map(_.returnedDate).update(returnedDateString)).map(_ => ())
  }
}