package business

import java.time.LocalDate

case class LoanBookDTO(memberID : LibraryMemberID, loanedDate : LocalDate, dueDate : LocalDate) {
  
}