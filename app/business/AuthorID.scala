package business

case class AuthorID(val asInt : Int) extends AnyVal {
  override def toString =
    asInt.toString
}