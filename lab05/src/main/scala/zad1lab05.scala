object zad1lab05 extends App {
//   // Zdefiniuj generyczną funkcję rekurencyjną:

  def isOrdered[A](leq: (A, A) => Boolean)(l: List[A]): Boolean = l match {
  case Nil => true
  case head :: Nil => true
  case x :: y :: tail => if (leq(x, y)) isOrdered(leq)(y :: tail) else false // x - pierwszy element listy y - drugi
}
// // która, dla zadanego porządku leq, sprawdzi czy elementy listy l ułożone są zgodnie z nim. W definicji użyj rekurencji ogonowej.

// // Przykład:

  val lt = (m: Int, n: Int) => m < n
  val lte = (m: Int, n: Int) => m <= n
  val lista = List(1, 2, 2, 5)
  println(isOrdered(lt)(lista)) // ==> false
  println(isOrdered(lte)(lista)) // ==> true
}
