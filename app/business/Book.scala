package business
import java.time.LocalDate

class Book(val id : BookID, var title : String, var publicationDate : LocalDate, var description : String, var isbn : ISBN) {
}