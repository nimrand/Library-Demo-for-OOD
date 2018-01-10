package controllers

import business._
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import play.api.data.Forms._
import play.api.data._
import scala.collection.immutable.Seq
import FormMappings._
import play.api.data.format.Formatter

object Forms {
  implicit val isbnFormat = new Formatter[ISBN] {
    def bind(key : String, data : Map[String, String]) = {
      ISBN.tryParseAndConvert(data.getOrElse(key, "")) match {
        case Success(isbn) => Right(isbn)
        case Error(message) => Left(List(FormError(key, message)))
      }
    }
    
    def unbind(key : String, isbn : ISBN) = Map(key -> isbn.toString)
  }
  
  val bookForm = Form(
    mapping(
      "title" -> nonEmptyText(maxLength=256),
      "authorID" -> number.transform[AuthorID](id => AuthorID(id), id => id.asInt),
      "publicationDate" -> localDate,
      "description" -> text,
      "isbn" -> of[ISBN], //nonEmptyText.verifying("Must be a valid 13-digit ISBN number.", isbn => ISBN.tryParse(isbn).successful).transform[ISBN](s => ISBN.tryParse(s).get, isbn => isbn.toString),
      "publisherID" -> number.transform[PublisherID](id => PublisherID(id), id => id.asInt),
      "price" -> bigDecimal(precision=10, scale=2),
      "keywords" -> tokens(1024),
      "callNumber" -> nonEmptyText(maxLength=32).verifying("Must be in the format '#.# A'.", callNumber => callNumber.matches("""\d+.\d+ [A-Z0-9]+"""))
    ) (EditBookDTO.apply) (EditBookDTO.unapply)
  )
  
  val registerPublisherForm = Form(single("name" -> nonEmptyText(maxLength=256)))
  
  val registerLibraryMemberForm = Form[EditLibraryMemberDTO](
      mapping(
          "firstName" -> text(maxLength=256),
          "middleName" -> text(maxLength=256),
          "lastName" -> nonEmptyText(maxLength=256),
          "suffixName" -> text(maxLength=256),
          "titles" -> tokens(256),
          "joinedDate" -> localDate
      ) { (firstName, middleName, lastName, suffixName, titles, joinedDate) => { EditLibraryMemberDTO(PersonName(firstName, middleName, lastName, suffixName, titles), joinedDate) } } (dto => Some((dto.name.firstName, dto.name.middleName, dto.name.lastName, dto.name.suffixName, dto.name.titles, dto.joinedDate)))
  )
  
  val searchBooksForm = Form(single("searchTerms" -> tokens(1024)))
  
  val loanBookForm = Form[LoanBookDTO](
      mapping(
          "memberID" -> number.transform[LibraryMemberID](id => LibraryMemberID(id), id => id.asInt),
          "loanedDate" -> localDate,
          "dueDate" -> localDate
      ) (LoanBookDTO.apply) (LoanBookDTO.unapply)
  )
  
  val reportBookReturnedForm = Form[LocalDate](single("returnedDate" -> localDate))
  
  val loginForm = Form(
    tuple(
      "username" -> text,
      "password" -> text
    )
  )
  
  val registerAuthorForm = Form[PersonName](
      mapping(
          "firstName" -> text(maxLength=256),
          "middleName" -> text(maxLength=256),
          "lastName" -> nonEmptyText(maxLength=256),
          "suffixName" -> text(maxLength=256),
          "titles" -> tokens(256)
      ) (PersonName.apply) (PersonName.unapply) // { (firstName, middleName, lastName, suffixName, titles) => { PersonName(firstName, middleName, lastName, suffixName, titles) } (name => Some((name.firstName, name.middleName, name.lastName, name.suffixName, name.titles)))
  )
}