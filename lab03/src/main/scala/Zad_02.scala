package lab03

object zad_02 extends App {
  def hipoteza(liczba: Int): Unit = {
    def czyPierwsza(n: Int): Boolean = {
      if (n <= 1) false
      else if (n == 2) true
      else !(2 to (n-1)).exists(x => n % x == 0)
    }

    def pomocnicza(liczba: Int, acc: List[Int]): Unit = {
      if (liczba == 0) {
        val pairs = for {
          x <- acc
          y <- acc
          if x <= y
        } yield (x, y)

        val validPairs = pairs.filter { case (x, y) => czyPierwsza(x) && czyPierwsza(y) }

        if (validPairs.nonEmpty) {
          println(s"Znaleziono pary liczb pierwszych, których suma wynosi $liczba:")
          validPairs.foreach { case (x, y) => println(s"$x + $y = $liczba") }
        } else {
          println("Nie znaleziono par liczb pierwszych, których suma wynosi $liczba")
        }
      } else if (liczba < 0) {
        ()
      } else {
        pomocnicza(liczba - 1, liczba :: acc)
        pomocnicza(liczba - 1, acc)
      }
    }

    pomocnicza(liczba / 2, List())
  }

  def zad_02(liczba: Int): Unit = {
    require(liczba > 2)
    require(liczba % 2 == 0)
    hipoteza(liczba)
  }
  println(zad_02(18))
}