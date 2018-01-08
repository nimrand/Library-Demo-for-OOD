package business

case class BookID(val asInt: Int) extends AnyVal {
  override def toString = asInt.toString
}