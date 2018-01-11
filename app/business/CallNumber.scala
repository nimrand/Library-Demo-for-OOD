package business
import math.BigDecimal

case class CallNumber(val classificationNumber : BigDecimal, val cutterNumber : String) {
  lazy val section =
    (classificationNumber / 100).min(9).toInt
  
  override def toString =
    s"$classificationNumber $cutterNumber"
}

object CallNumber extends ((BigDecimal, String) => CallNumber) {
  val regex = """(\d+.\d+)(\s+[A-Z\d]+)?"""r
  
  def tryParse(string : String) : CheckedConversion[CallNumber] = {
    val canonicalizedString = string.split("\\s+").mkString(" ").toUpperCase
    if(canonicalizedString.length > 32)
      InvalidFormatError("Must be less than 32 characters long.")
    canonicalizedString match {
      case regex(numberString, cutterNumberString) => 
        Success(CallNumber(BigDecimal(numberString), cutterNumberString.trim))
      case _ =>
        InvalidFormatError("Invalid format.  Must be a dewey decimal number of the format ###.### followed by a cutter number, which may consist of letters and numbers.")
    }
  }
}