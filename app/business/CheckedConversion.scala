package business

sealed abstract class CheckedConversion[+T]() {
  def get() : T
  def asOption() : Option[T]
  def successful() : Boolean
}

case class Success[+T](value : T) extends CheckedConversion[T] {
  def get() = value
  def asOption() = Some(value)
  def successful() = true
}

class Error(val message : String) extends CheckedConversion[Nothing] {
  def get() = throw new Exception(message)
  def asOption() = None
  def successful() = false
}

object Error {
  def unapply(value : Any) : Option[String] =
    if(value.isInstanceOf[Error]) {
      Some(value.asInstanceOf[Error].message)
    } else {
      None
    }
}