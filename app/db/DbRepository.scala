package db

import business._
import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }
import scala.collection.immutable._
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Singleton
class DbRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) extends Repository {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  
  private val dateFormat =  DateTimeFormatter.BASIC_ISO_DATE

  import dbConfig._
  import profile.api._
  
  private class PublisherTable(tag: Tag) extends Table[Publisher](tag, "Publisher") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")
    
    def * = (id, name) <> ((fields : (Int, String)) => new Publisher(PublisherID(fields._1), fields._2), (publisher: Publisher) => Some((publisher.id.asInt, publisher.name)))
  }
  
  private val publishers = TableQuery[PublisherTable]
  
  case class BookRecord(id : BookID, title : String, isbn : ISBN, price : BigDecimal, keywords : Seq[String], description : String, callNumber : String, publicationDate : LocalDate, publisherID : PublisherID)
  
  private class BookTable(tag: Tag) extends Table[BookRecord](tag, "Book") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    
    def title = column[String]("title")
    def isbn = column[String]("isbn")
    def price = column[String]("price")
    def keywords = column[String]("keywords")
    def description = column[String]("description")
    def callNumber = column[String]("call_number")
    def publicationDate = column[String]("publication_date")
    def publisherID = column[Int]("publisher_id")
    
    def * = (id, title, isbn, price, keywords, description, callNumber, publicationDate, publisherID) <> ((fields : (Int, String, String, String, String, String, String, String, Int)) => BookRecord(BookID(fields._1), fields._2, ISBN.tryParse(fields._3).get, BigDecimal(fields._4), fields._5.split(" ").to[Seq], fields._6, fields._7, LocalDate.parse(fields._8, dateFormat), PublisherID(fields._9)), (book: BookRecord) => Some((book.id.asInt, book.title, book.isbn.toString, book.price.toString, book.keywords.mkString(" "), book.description, book.callNumber, book.publicationDate.format(dateFormat), book.publisherID.asInt)))
  }
  
  private val books = TableQuery[BookTable]
  private val bookFields = books.map(p => (p.title, p.isbn, p.price, p.keywords, p.description, p.callNumber, p.publicationDate, p.publisherID))
  
  implicit class RichEditBookDTO(book : EditBookDTO) {
    def fields =
      (book.title, book.isbn.toString, book.price.toString, book.keywords.mkString(" "), book.description, book.callNumber, book.publicationDate.format(dateFormat), book.publisherID.asInt)
  }
  
  private class PersonNameTable(tag: Tag) extends Table[(Int, PersonName)](tag, "PersonName") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    
    def firstName = column[String]("firstName")
    def middleName = column[String]("middleName")
    def lastName = column[String]("lastName")
    def suffixName = column[String]("suffixName")
    def titles = column[String]("titles")
    
    def * = (id, firstName, middleName, lastName, suffixName, titles) <> (
        (fields : (Int, String, String, String, String, String)) => (fields._1, new PersonName(fields._2, fields._3, fields._4, fields._5, fields._6.split(" ").to[Seq])), (personNameRecord: (Int, PersonName)) => Some((personNameRecord._1, personNameRecord._2.firstName, personNameRecord._2.middleName, personNameRecord._2.lastName, personNameRecord._2.suffixName, personNameRecord._2.titles.mkString(" "))))
  }
  
  private val personNames = TableQuery[PersonNameTable]
  
  private class LibraryMemberTable(tag: Tag) extends Table[(Int, Int, LocalDate)](tag, "PersonName") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    
    def nameID = column[Int]("name_id")
    def joinedDate = column[String]("joined_date")
    
    def * = (id, nameID, joinedDate) <> (
        (fields : (Int, Int, String)) => (fields._1, fields._2, LocalDate.parse(fields._3, dateFormat)), (libraryMemberRecord: (Int, Int, LocalDate)) => Some((libraryMemberRecord._1, libraryMemberRecord._2, libraryMemberRecord._3.format(dateFormat))))
  }
  
  private val libraryMembers = TableQuery[LibraryMemberTable]
  
  def createPublisher(name : String) : Future[PublisherID] = db.run {
    publishers.map(p => p.name) returning publishers.map(_.id) into ((name, id) => PublisherID(id)) += (name)
  }
  
  def getPublishers() : Future[Seq[Publisher]] = db.run {
    publishers.result
  }.map(_.to[Seq])
  
  def createBook(book : EditBookDTO) : Future[BookID] = {
    db.run {
      (bookFields
          returning books.map(_.id)
          into ((fields, id) => BookID(id))
      ) += book.fields
    }
  }
  
  def retrieveBook(bookID : BookID) : Future[Book] = {
    db.run(books.filter(_.id === bookID.asInt).result.head).flatMap{ bookRecord =>
      db.run(publishers.filter(_.id === bookRecord.publisherID.asInt).result.head).map{ publisherRecord =>
        new Book(bookRecord.id, bookRecord.title, bookRecord.isbn, bookRecord.price, bookRecord.keywords, bookRecord.description, bookRecord.callNumber, bookRecord.publicationDate, new Publisher(bookRecord.publisherID, publisherRecord.name))
      }
    }
  }
  
  def editBook(bookID : BookID, book : EditBookDTO) : Future[Unit] =
    db.run(bookFields.update(book.fields)).map(_ => ())
  
  def createLibraryMember(libraryMember : LibraryMember) : Future[LibraryMemberID] = {
    val nameColumns = (libraryMember.name.firstName, libraryMember.name.middleName, libraryMember.name.lastName, libraryMember.name.suffixName, libraryMember.name.titles.mkString(" "))
    db.run{
      (personNames.map(n => (n.firstName, n.middleName, n.lastName, n.suffixName, n.titles))
          returning personNames.map(_.id)
          into ((fields, id) => id)
      ) += nameColumns
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
}