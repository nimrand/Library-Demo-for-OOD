package business
import java.time.LocalDate
import scala.collection.immutable.Seq

class Book(val id : BookID, val title : String, val isbn : ISBN, val price : BigDecimal, val keywords : Seq[String], val description : String, val callNumber : String, val publicationDate : LocalDate, val publisher : Publisher, val status : BookStatus) {
  def toEditDTO() =
    EditBookDTO(title, publicationDate, description, isbn, publisher.id, price, keywords, callNumber)
}