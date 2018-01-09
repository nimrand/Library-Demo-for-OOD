package database

import javax.inject.{ Inject, Singleton }
import business._
import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }
import scala.collection.immutable._
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Singleton
class DbSchema @Inject() (dbConfigProvider: DatabaseConfigProvider) {
  
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  
  val dateFormat =  DateTimeFormatter.BASIC_ISO_DATE

  import dbConfig._
  import profile.api._
  
  class PublisherTable(tag: Tag) extends Table[Publisher](tag, "Publisher") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")
    
    def * = (id, name) <> ((fields : (Int, String)) => new Publisher(PublisherID(fields._1), fields._2), (publisher: Publisher) => Some((publisher.id.asInt, publisher.name)))
  }
  
  val publishers = TableQuery[PublisherTable]
  
  implicit class RichBookStatus(status : BookStatus) {
    def toCode =
      status match {
        case BookStatus.Available => 0
        case BookStatus.CheckedOut => 1
        case BookStatus.Lost => 2
        case BookStatus.Disposed => 3
      }
  }
  
  implicit class RichStatusCode(statusCode : Int) {
    def toStatus =
      if(statusCode == 0) {
        BookStatus.Available
      } else if(statusCode == 1) {
        BookStatus.CheckedOut
      } else if(statusCode == 2) {
        BookStatus.Lost
      } else {
        BookStatus.Disposed
      }
  }
  
  case class BookRecord(id : BookID, title : String, isbn : ISBN, price : BigDecimal, keywords : Seq[String], description : String, callNumber : String, publicationDate : LocalDate, publisherID : PublisherID, statusCode : Int) {
    def toBookListing =
      BookListing(id, title, keywords, description, isbn, callNumber, statusCode.toStatus)
    
    def toBook(publisher : Publisher) =
      new Book(id, title, isbn, price, keywords, description, callNumber, publicationDate, publisher, statusCode.toStatus)
  }
  
  class BookTable(tag: Tag) extends Table[BookRecord](tag, "Book") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    
    def title = column[String]("title")
    def isbn = column[String]("isbn")
    def price = column[String]("price")
    def keywords = column[String]("keywords")
    def description = column[String]("description")
    def callNumber = column[String]("call_number")
    def publicationDate = column[String]("publication_date")
    def publisherID = column[Int]("publisher_id")
    def statusCode = column[Int]("status_code")
    
    def publisher = foreignKey("", publisherID, publishers)(_.id)
    
    def * = (id, title, isbn, price, keywords, description, callNumber, publicationDate, publisherID, statusCode) <> ((fields : (Int, String, String, String, String, String, String, String, Int, Int)) => BookRecord(BookID(fields._1), fields._2, ISBN.tryParse(fields._3).get, BigDecimal(fields._4), fields._5.split(" ").to[Seq], fields._6, fields._7, LocalDate.parse(fields._8, dateFormat), PublisherID(fields._9), fields._10), (book: BookRecord) => Some((book.id.asInt, book.title, book.isbn.toString, book.price.toString, book.keywords.mkString(" "), book.description, book.callNumber, book.publicationDate.format(dateFormat), book.publisherID.asInt, book.statusCode)))
  }
  
  val books = TableQuery[BookTable]
  
  val bookFields = books.map(p => (p.title, p.isbn, p.price, p.keywords, p.description, p.callNumber, p.publicationDate, p.publisherID))
  
  class PersonNameTable(tag: Tag) extends Table[(Int, PersonName)](tag, "PersonName") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    
    def firstName = column[String]("first_name")
    def middleName = column[String]("middle_name")
    def lastName = column[String]("last_name")
    def suffixName = column[String]("suffix_name")
    def titles = column[String]("titles")
    
    def * = (id, firstName, middleName, lastName, suffixName, titles) <> (
        (fields : (Int, String, String, String, String, String)) => (fields._1, new PersonName(fields._2, fields._3, fields._4, fields._5, fields._6.split(" ").to[Seq])), (personNameRecord: (Int, PersonName)) => Some((personNameRecord._1, personNameRecord._2.firstName, personNameRecord._2.middleName, personNameRecord._2.lastName, personNameRecord._2.suffixName, personNameRecord._2.titles.mkString(" "))))
  }
  
  val personNames = TableQuery[PersonNameTable]
  
  class LibraryMemberTable(tag: Tag) extends Table[(Int, Int, LocalDate)](tag, "LibraryMember") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    
    def nameID = column[Int]("name_id")
    def joinedDate = column[String]("joined_date")
    
    def * = (id, nameID, joinedDate) <> (
        (fields : (Int, Int, String)) => (fields._1, fields._2, LocalDate.parse(fields._3, dateFormat)), (libraryMemberRecord: (Int, Int, LocalDate)) => Some((libraryMemberRecord._1, libraryMemberRecord._2, libraryMemberRecord._3.format(dateFormat))))
  }
  
  val libraryMembers = TableQuery[LibraryMemberTable]
  
  class BookLoanTable(tag : Tag) extends Table[(Int, Int, Int, String, String, Option[String])](tag, "BookLoan") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    
    def bookID = column[Int]("book_id")
    def memberID = column[Int]("member_id")
    def loanedDate = column[String]("loaned_date")
    def dueDate = column[String]("due_date")
    def returnedDate = column[Option[String]]("returned_date")
    
    def * = (id, bookID, memberID, loanedDate, dueDate, returnedDate)
  }
  
  val bookLoans = TableQuery[BookLoanTable]
  
  class UserTable(tag : Tag) extends Table[(String, String)](tag, "User") {
    def username = column[String]("username")
    def password = column[String]("password")
    
    def * = (username, password)
  }
  
  val users = TableQuery[UserTable]
}