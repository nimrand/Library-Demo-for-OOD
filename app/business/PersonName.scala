package business

import scala.collection.immutable.Seq

case class PersonName(val firstName : String, val middleName : String, val lastName : String, val suffixName : String, val titles : Seq[String]) {
  override def toString =
    (Seq(firstName, middleName, lastName, suffixName).filter(_ != "") ++ titles).mkString(" ")
}

object PersonName {
  val empty = PersonName("", "", "", "", Seq())
}