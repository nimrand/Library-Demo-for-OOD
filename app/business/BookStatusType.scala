package business

sealed case class BookStatusType private(name : String) {
}

object BookStatusType {
  val Available = BookStatusType("Available")
  val CheckedOut = BookStatusType("Checked Out")
  val Lost = BookStatusType("Lost")
  val Disposed = BookStatusType("Disposed")
}