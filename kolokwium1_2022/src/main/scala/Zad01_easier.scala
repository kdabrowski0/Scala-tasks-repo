def ranking1(): List[(Int, Int)] = {
  val input = io.Source
    .fromResource("test.txt")
    .getLines
    .map(_.split(" ").toList)
    .toList
    
  val atLeast50 = input.drop(1).length/2
 
  val result = input
  .transpose
  .drop(1)
  .map(el => (el.head, el.tail.map(_.toInt).sum))
  .sortBy(-_._2)
  .zipWithIndex
  .flatMap(el => List((el._2 + 1, el._1._1.toInt, el._1._2))) //el._2 is an index and el._1 is a tuple so el._1._1 is a first element of a tuple
  .filter(el => el._3 >= atLeast50)
  .foldLeft(List[(Int, Int, Int)]()) {
    case (acc, (el)) =>
      if (el._1 == 1) {
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
def zadanie_1_My: Unit = {
  println(ranking1())
}

// val matrix = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
// val transposed = matrix.transpose
// println(transposed)
// List(List(1, 4, 7), List(2, 5, 8), List(3, 6, 9))
// Original matrix:
// 1 2 3
// 4 5 6
// 7 8 9

// Transposed matrix:
// 1 4 7
// 2 5 8
// 3 6 9