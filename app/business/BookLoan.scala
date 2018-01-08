package business
import java.time.LocalDate

class BookLoan(val id : BookLoanID, val bookID : BookID, val member : LibraryMember, val loanedDate : LocalDate, val dueDate : LocalDate, val returnedDate : Option[LocalDate]) {
}