package business

class Author(val id : AuthorID, val name : PersonName) {
  override def toString =
    name.toString
}