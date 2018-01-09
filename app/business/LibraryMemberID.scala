package business

case class LibraryMemberID(val asInt: Int) extends AnyVal {
  override def toString =
    f"${asInt / 1000}%04d-${asInt % 1000}%03d"
}