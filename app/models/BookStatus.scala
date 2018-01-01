package models
import collection.immutable.Seq

class BookStatus(val id : BookID, initialStatus : BookStatusType, initialLoans : Seq[BookLoan]) {
  private var _loans = Seq(initialLoans)
  private var _status = initialStatus;
  def loans = _loans
  def status = _status
}