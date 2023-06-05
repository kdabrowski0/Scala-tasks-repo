object zad2lab05 extends App {
  // Korzystając z metody foldLeft zdefiniuj generyczną funkcję
def deStutter[A](list: List[A]): List[A] = {
    list.foldLeft(List[A]()){
      case (acc, el) => if (acc.isEmpty || acc.last != el) acc :+ el else acc
    }
}
  // „kompresującą” na liscie list wszystkie powtarzające się podciągi.

  // Przykład:
  val l = List(1, 1, 2, 4, 4, 4, 1, 3)
  assert(deStutter(l) == List(1, 2, 4, 1, 3)) // ==> true
  println(deStutter(l))
}