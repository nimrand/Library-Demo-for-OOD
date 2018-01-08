package business

sealed case class BookStatus private(name : String) {
}

object BookStatus {
  val Available = BookStatus("Available")
  val CheckedOut = BookStatus("Checked Out")
  val Lost = BookStatus("Lost")
  val Disposed = BookStatus("Disposed")
}