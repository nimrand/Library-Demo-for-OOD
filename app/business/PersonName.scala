package business

import scala.collection.immutable.Seq

case class PersonName(val firstName : String, val middleName : String, val lastName : String, val suffixName : String, val titles : Seq[String]) {
  override def equals(other : Any) =
    other match {
      case otherName : PersonName => {
        firstName == otherName.firstName && middleName == otherName.middleName && lastName == otherName.lastName && suffixName == otherName.suffixName && titles.sameElements(otherName.titles)
      }
      case _ => false
    }
  
  override def toString =
    (Seq(firstName, middleName, lastName, suffixName).filter(_ != "") ++ titles).mkString(" ")
}

object PersonName extends ((String, String, String, String, Seq[String]) => PersonName) {
  val empty = PersonName("", "", "", "", Seq())
}