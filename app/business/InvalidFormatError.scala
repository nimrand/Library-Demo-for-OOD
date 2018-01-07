package business

class InvalidFormatError(message : String = "Invalid format.") extends Error(message) {  
}

object InvalidFormatError {
  def apply(message : String = "Invalid format.") =
    new InvalidFormatError(message)
  
  def unapply(value : Any) =
    if(value.isInstanceOf[InvalidFormatError]) {
      Some(value.asInstanceOf[InvalidFormatError].message)
    } else {
      None
    }
}
