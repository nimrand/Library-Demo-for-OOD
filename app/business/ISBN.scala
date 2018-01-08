package business
import Digits.digitsOf

class ISBN private(val serialNumber : Long) {
  require(serialNumber >= 0 && serialNumber < 1000000000000L, "title must be a positive, 13-digit number.")
  
  def digits = digitsOf(serialNumber, 12)
  
  val checksum =
    (10 - (digits.zipWithIndex.map{ case(x, y) => x * (if(y % 2 == 0) 1 else 3) }.sum % 10)) % 10
  
  override def toString =
    serialNumber.toString + "-" + checksum.toString
}

object ISBN {
  private val PatternRegex = """([0-9](-| )?){12}[0-9]""".r
  
  def tryParse(string : String) : CheckedConversion[ISBN] = {
    if(string.trim().matches("""([0-9](-| )?){12}[0-9xX]""")) {
        val canonicalString = string.replaceAll(" ", "").replaceAll("-", "")
        val isbn = new ISBN(canonicalString.substring(0, 12).toLong)
        val checksum = canonicalString.charAt(12).toString.toInt
        if(checksum != isbn.checksum)
          InvalidChecksumError(checksum, isbn.checksum)
        else
          Success(isbn)
  } else {
        InvalidFormatError("Invalid format.  Expected 13 digits.  Dashes and spaces are allowed.")
    }
  }
  
  def fromLong(serialNumber : Long) =
    new ISBN(serialNumber)
}