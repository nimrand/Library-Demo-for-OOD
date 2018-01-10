package business
import Math.pow

object Digits {
  def digit(number : Long, i : Int) =
    (number / pow(10, i) % 10).toInt
  def digitsOf(number : Long, n : Int) =
    for(i <- (n - 1) to 0 by -1)
      yield digit(number, i)
}