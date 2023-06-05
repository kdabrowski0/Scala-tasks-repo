def wystąpienia(arg: List[Int]): List[(Int, Int)] = {
  def wystapieniaRec(acc: Map[Int, Int], arg: List[Int]): Map[Int, Int] = {
    arg match {
      case Nil => acc
      case head :: tail =>
        val counter = acc.getOrElse(head, 0) + 1
        wystapieniaRec(acc.updated(head, counter), tail)
    }
  }
  wystapieniaRec(Map.empty, arg).toList 
}

@main def zad_1: Unit = {
  // „program główny” ma znaczenie czysto pomocnicze
  val arg = List(1,2,3,3,2,1)
  val wyn = wystąpienia(arg)
  println(wyn)
}