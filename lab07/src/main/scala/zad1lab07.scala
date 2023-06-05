object zad1lab07 extends App {
  // Używając metod filter, map i zipWithIndex zdefiniuj funkcję:

  def usuń[A](lista: List[A], k: Int): List[A] = {
    val newList = lista.zipWithIndex
    newList.filter(_._2 != k).map(_._1).toList
  }

  val lista = List(1,2,3,4,5,6) 
  println(usuń(lista, 4))
// usuwającą k-ty element listy lista.
}