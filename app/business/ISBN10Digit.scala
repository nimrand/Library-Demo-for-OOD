package business
import Digits.digitsOf

class ISBN10Digit private(val serialNumber : Long) {
  require(serialNumber >= 0 && serialNumber < 1000000000L, "must be a positive, 10-digit number.")
  
  def digits = digitsOf(serialNumber, 12)
  
  def to13DigitISBN = ISBN.fromLong(978000000000L + serialNumber)
  
  val checksum = ISBN10Digit.checksumFromOrdinal(11 - (digits.zip(11 to 2 by -1).map{ case(x, y) => x * y }.sum % 11))
}

object ISBN10Digit {
  private val patternRegex = """([0-9](-| )?){9}[0-9xX]""".r
  
  def checksumFromOrdinal(int : Int) =
    if(int == 11)
      'X'
    else
      int.toString()
      
  def tryParse(value : String) = {
     value.trim() match {
        case patternRegex.anchored() =>
          val canonicalString = value.replaceAll(" ", "").replaceAll("-", "")
          val isbn = new ISBN10Digit(canonicalString.substring(0, 9).toLong)
          val checksum = canonicalString.charAt(9)
          if(checksum != isbn.checksum)
            InvalidChecksumError(checksum, isbn.checksum)
          else
            Success(isbn)
        case _ =>
          InvalidFormatError("Invalid format.  Expected 9 digits followed by a checksum, which may be a digit or X.  Dashes and spaces are allowed.")
      }
   }
}