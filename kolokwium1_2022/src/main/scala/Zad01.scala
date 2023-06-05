/*
  UWAGA! Uzupełnij kod funkcji „ranking” zgodnie z treścią zadania.
         Z poziomu SBT wydaj polecenie „test” żeby sprawdzić, czy
         Twoje rozwiązanie przechodzi przygotowane testy jednostkowe.
         Możesz dzięki temu nie tworzyć „programu głównego”.
*/

def ranking(): List[(Int, Int)] = {
  val input = io.Source
    .fromResource("test.txt")
    .getLines
    .map(_.split(" ").toList)
    .toList

  val minRes = input.drop(1).length/2

  val result = input
    .transpose
    .drop(1)
    .map(el => (el.head, el.tail.map(_.toInt).sum))
    .sortBy(-_._2)
    .zipWithIndex
    .flatMap(el => List((el._2 + 1, el._1._1.toInt, el._1._2)))
    .filter(el => el._3 >= minRes)
    .zipWithIndex
    .foldLeft(List[(Int, Int, Int)]()) {
      case (acc, (el, i)) =>
        if (i == 0) {
          acc :+ el
        } else {
          if (el._3 == acc.last._3) {
            acc :+ (acc.last._1, el._2, el._3)
          } else {
            acc :+ el
          }
        }
    }
    .map(el => (el._1, el._2))

  result

}

@main
def zadanie_1: Unit = {
  println(ranking())
}
