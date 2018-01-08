package business
import java.time.LocalDate

class BookLoan(val bookLoanID : BookLoanID, val bookID : BookID, val memberID : LibraryMemberID, val loanedDate : LocalDate, private var _dueDate : LocalDate, private var _returnedDate : LocalDate) {
  def dueDate : LocalDate = _dueDate
  def returnedDate : LocalDate = _returnedDate
}