package business

sealed abstract class BookSearchSort(val name : String) {
  override def toString = name
}

object BookSearchSort {
  object relevance extends BookSearchSort("Relevance")
  object title extends BookSearchSort("Title")
  object authorLastName extends BookSearchSort("AuthorLastName")
  object authorFirstName extends BookSearchSort("AuthorFirstName")
  
  val default = relevance
  val all = Seq(relevance, title, authorLastName, authorFirstName)
  val nameMap = all.map(sort => sort.name -> sort).toMap
}
