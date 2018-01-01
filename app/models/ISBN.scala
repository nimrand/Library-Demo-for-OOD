package models
import Digits.digitsOf

class ISBN private(val serialNumber : Long) {
  require(serialNumber >= 0 && serialNumber < 1000000000000L, "title must be a positive, 13-digit number.")
  
  def digits = digitsOf(serialNumber, 12)
  
  val checksum =
    10 - digits.zipWithIndex.map{ case(x, y) => x * (if(y % 2 == 1) 1 else 3) }.sum % 10
}

object ISBN {
  private val patternRegex = """([0-9](-| )?){12}[0-9xX]""".r
  
  def tryParse(string : String) = {
    string.trim() match {
      case patternRegex.anchored() =>
        val canonicalString = string.replaceAll(" ", "").replaceAll("-", "")
        val isbn = new ISBN(canonicalString.substring(0, 12).toLong)
        val checksum = canonicalString.charAt(12).toInt
        if(checksum != isbn.checksum)
          InvalidChecksumError(checksum, isbn.checksum)
        else
          Success(isbn)
      case _ =>
        InvalidFormatError("Invalid format.  Expected 13 digits.  Dashes and spaces are allowed.")
    }
  }
  
  def fromLong(serialNumber : Long) =
    new ISBN(serialNumber)
}