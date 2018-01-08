package business
import java.time.LocalDate
import scala.collection.immutable.Seq

class Book(val id : BookID, var title : String, var isbn : ISBN, var price : BigDecimal, var keywords : Seq[String], var description : String, var callNumber : String, var publicationDate : LocalDate, var publisher : Publisher) {
  def toEditDTO() =
    EditBookDTO(title, publicationDate, description, isbn, publisher.id, price, keywords, callNumber)
}