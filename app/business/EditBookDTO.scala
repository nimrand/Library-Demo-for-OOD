package business
import java.time.LocalDate
import scala.collection.immutable.Seq

case class EditBookDTO(title : String, authorID : AuthorID, publicationDate : LocalDate, description : String, isbn : ISBN, publisherID : PublisherID, price : BigDecimal, keywords : Seq[String], callNumber : CallNumber) {
  
}