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
  
  implicit val authorIDColumnType = MappedColumnType.base[AuthorID, Int](_.asInt, AuthorID(_))
  implicit val personNameIDColumnType = MappedColumnType.base[PersonNameID, Int](_.asInt, PersonNameID(_))
  implicit val bookIDColumnType = MappedColumnType.base[BookID, Int](_.asInt, BookID(_))
  implicit val bookLoanIDColumnType = MappedColumnType.base[BookLoanID, Int](_.asInt, BookLoanID(_))
  implicit val publisherIDColumnType = MappedColumnType.base[PublisherID, Int](_.asInt, PublisherID(_))
  implicit val libraryMemberIDColumnType = MappedColumnType.base[LibraryMemberID, Int](_.asInt, LibraryMemberID(_))
  implicit val localDateColumnType = MappedColumnType.base[LocalDate, String](_.format(dateFormat), LocalDate.parse(_, dateFormat))
  implicit val isbnColumnType = MappedColumnType.base[ISBN, String](_.toString, ISBN.tryParse(_).get)
  implicit val callNumberColumnType = MappedColumnType.base[CallNumber, String](_.toString, CallNumber.tryParse(_).get)
  implicit val tokensColumnType = MappedColumnType.base[Seq[String], String](_.mkString(" "), _.split(" ").to[Seq])
  
  class PublisherTable(tag: Tag) extends Table[Publisher](tag, "Publisher") {
    def id = column[PublisherID]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")
    
    def * = (id, name) <> ((fields : (PublisherID, String)) => new Publisher(fields._1, fields._2), (publisher: Publisher) => Some((publisher.id, publisher.name)))
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
  
  implicit val bookStatusColumnType = MappedColumnType.base[BookStatus, Int](_.toCode, _.toStatus)
  /*
  case class BookRecord(id : BookID, title : String, authorID : AuthorID, isbn : ISBN, price : BigDecimal, keywords : Seq[String], description : String, callNumber : String, publicationDate : LocalDate, publisherID : PublisherID, statusCode : BookStatus) {
    def toBookListing(authorName : PersonName) =
      BookListing(id, title, new Author(authorID, authorName), keywords, description, isbn, callNumber, statusCode)
    
    def toBook(author : Author, publisher : Publisher) =
      new Book(id, title, author, isbn, price, keywords, description, callNumber, publicationDate, publisher, statusCode)
  }*/
  
  class BookTable(tag: Tag) extends Table[(BookID, EditBookDTO, BookStatus)](tag, "Book") {
    def id = column[BookID]("id", O.PrimaryKey, O.AutoInc)
    
    def title = column[String]("title")
    def authorID = column[AuthorID]("author_id")
    def isbn = column[ISBN]("isbn")
    def price = column[BigDecimal]("price")
    def keywords = column[Seq[String]]("keywords")
    def description = column[String]("description")
    def callNumber = column[CallNumber]("call_number")
    def publicationDate = column[LocalDate]("publication_date")
    def publisherID = column[PublisherID]("publisher_id")
    def status = column[BookStatus]("status_code")
    
    def publisher = foreignKey("", publisherID, publishers)(_.id)
    def author = foreignKey("", authorID, authors)(_.id)
    
    def properties = (title, authorID, publicationDate, description, isbn, publisherID, price, keywords, callNumber) <> (EditBookDTO.tupled, EditBookDTO.unapply)
    
    def * = (id, properties, status) //<> (BookRecord.tupled, BookRecord.unapply)
  }
  
  val books = TableQuery[BookTable]
  
  class PersonNameTable(tag: Tag) extends Table[(PersonNameID, PersonName)](tag, "PersonName") {
    def id = column[PersonNameID]("id", O.PrimaryKey, O.AutoInc)
    
    def firstName = column[String]("first_name")
    def middleName = column[String]("middle_name")
    def lastName = column[String]("last_name")
    def suffixName = column[String]("suffix_name")
    def titles = column[Seq[String]]("titles")
    
    def name = (firstName, middleName, lastName, suffixName, titles) <> (PersonName.tupled, PersonName.unapply)
    
    def * = (id, name)
        //(fields : (PersonNameID, String, String, String, String, Seq[String])) => (fields._1, new PersonName(fields._2, fields._3, fields._4, fields._5, fields._6)), (personNameRecord: (PersonNameID, PersonName)) => Some((personNameRecord._1, personNameRecord._2.firstName, personNameRecord._2.middleName, personNameRecord._2.lastName, personNameRecord._2.suffixName, personNameRecord._2.titles)))
  }
  
  val personNames = TableQuery[PersonNameTable]
  
  class LibraryMemberTable(tag: Tag) extends Table[(LibraryMemberID, PersonNameID, LocalDate)](tag, "LibraryMember") {
    def id = column[LibraryMemberID]("id", O.PrimaryKey, O.AutoInc)
    
    def nameID = column[PersonNameID]("name_id")
    def joinedDate = column[LocalDate]("joined_date")
    
    def name = foreignKey("", nameID, personNames)(_.id)
    
    def * = (id, nameID, joinedDate) <> (
        (fields : (LibraryMemberID, PersonNameID, LocalDate)) => (fields._1, fields._2, fields._3), (libraryMemberRecord: (LibraryMemberID, PersonNameID, LocalDate)) => Some((libraryMemberRecord._1, libraryMemberRecord._2, libraryMemberRecord._3)))
  }
  
  val libraryMembers = TableQuery[LibraryMemberTable]
  
  class BookLoanTable(tag : Tag) extends Table[(BookLoanID, BookID, LibraryMemberID, LocalDate, LocalDate, Option[LocalDate])](tag, "BookLoan") {
    def id = column[BookLoanID]("id", O.PrimaryKey, O.AutoInc)
    
    def bookID = column[BookID]("book_id")
    def memberID = column[LibraryMemberID]("member_id")
    def loanedDate = column[LocalDate]("loaned_date")
    def dueDate = column[LocalDate]("due_date")
    def returnedDate = column[Option[LocalDate]]("returned_date")
    
    def libraryMember = foreignKey("", memberID, libraryMembers)(_.id)
    def book = foreignKey("", bookID, books)(_.id)
    
    def * = (id, bookID, memberID, loanedDate, dueDate, returnedDate)
  }
  
  val bookLoans = TableQuery[BookLoanTable]
  
  class UserTable(tag : Tag) extends Table[(String, String)](tag, "User") {
    def username = column[String]("username")
    def password = column[String]("password")
    
    def * = (username, password)
  }
  
  val users = TableQuery[UserTable]
  
  class AuthorTable(tag : Tag) extends Table[(AuthorID, PersonNameID)](tag, "Author") {
    def id = column[AuthorID]("id", O.PrimaryKey, O.AutoInc)
    def nameID = column[PersonNameID]("name_id")
    
    def name = foreignKey("", nameID, personNames)(_.id)
    
    def * = (id, nameID)
  }
  
  val authors = TableQuery[AuthorTable]
}