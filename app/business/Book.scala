package business
import java.time.LocalDate
import scala.collection.immutable.Seq

class Book(val id : BookID, val title : String, val author : Author, val isbn : ISBN, val price : BigDecimal, val keywords : Seq[String], val description : String, val callNumber : CallNumber, val publicationDate : LocalDate, val publisher : Publisher, val status : BookStatus) {
  def this(id : BookID, properties : EditBookDTO, authorName : PersonName, publisherName : String, status : BookStatus) =
    this(id, properties.title, new Author(properties.authorID, authorName), properties.isbn, properties.price, properties.keywords, properties.description, properties.callNumber, properties.publicationDate, new Publisher(properties.publisherID, publisherName), status)
  
  def toEditDTO() =
    EditBookDTO(title, author.id, publicationDate, description, isbn, publisher.id, price, keywords, callNumber)
}