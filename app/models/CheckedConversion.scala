package models

sealed class CheckedConversion[+T]() {
  
}

case class Success[+T](value : T) extends CheckedConversion[T] {
}

class Error(val message : String) extends CheckedConversion[Any] {
}

object Error {
  def unapply(value : Any) : Option[String] =
    if(value.isInstanceOf[Error]) {
      Some(value.asInstanceOf[Error].message)
    } else {
      None
    }
}