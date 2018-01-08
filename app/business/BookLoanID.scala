package business

case class BookLoanID(val asInt: Int) extends AnyVal {
  override def toString = asInt.toString
}