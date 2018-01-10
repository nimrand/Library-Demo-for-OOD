package business

sealed abstract class BookSearchSort(val name : String) {
  override def toString = name
}

object BookSearchSort {
  object Relevance extends BookSearchSort("Relevance")
  object Title extends BookSearchSort("Title")
  object AuthorLastName extends BookSearchSort("AuthorLastName")
  object AuthorFirstName extends BookSearchSort("AuthorFirstName")
  
  val all = Seq(Relevance, Title, AuthorLastName, AuthorFirstName)
  val nameMap = all.map(sort => sort.name -> sort).toMap
}
