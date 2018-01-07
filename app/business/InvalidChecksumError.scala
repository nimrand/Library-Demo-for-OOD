package business

case class InvalidChecksumError[+T](found : T, expected : T) extends Error(s"The checksum $found did not match the expected value.") {
}