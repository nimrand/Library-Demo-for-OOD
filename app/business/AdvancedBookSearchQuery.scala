package business

case class AdvancedBookSearchQuery(val title : String, val authorName : String, publisherName : String, description : String, callNumber : String, isbn : String) {
  def isEmpty =
    title == "" && authorName == "" && publisherName == "" && description == "" && callNumber == "" && isbn == ""
}