package business

class BusinessException(message : String) extends Exception(message) {
  
}

object BusinessException {
  def unapply(obj : Any) : Option[String] =
    if(obj.isInstanceOf[BusinessException]) Some(obj.asInstanceOf[BusinessException].getMessage()) else None
}