package business

class Author(val authorID : AuthorID, val name : PersonName) {
  override def toString =
    name.toString
}