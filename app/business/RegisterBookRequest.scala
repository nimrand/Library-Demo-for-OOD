package business
import java.time.LocalDate
import scala.collection.immutable.Seq

case class RegisterBookRequest(title : String, publicationDate : LocalDate, description : String, isbn : ISBN, publisher : PublisherID, price : BigDecimal, keywords : Seq[String], callNumber : String) {
  
}