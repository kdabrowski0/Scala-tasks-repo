object zad4lab07 extends App {
// Korzystając z metod oferowanych przez kolekcje zdefiniuj funkcję:

def przestaw[A](l: List[A]): List[A] = {
  l.grouped(2).flatMap {
    case List(a,b) => List(b, a)
    case List(a) => List(a)
    case _ => Nil
  }.toList
}
        
// zamieniającą kolejnością wartości znajdujące się na parzystych i nieparzystych indeksach.

// Przykłady:
    val lista3 = List(1, 2, 3, 4, 5, 6)
    val lista1 = List(2, 1, 4, 3, 5)
    val lista = List(1, 2, 3, 4, 5)
    assert( przestaw(List(1, 2, 3, 4, 5)) ==  List(2, 1, 4, 3, 5) ) // ==> true
    assert( przestaw(List(1)) == List(1) )                      // ==> true
    assert( przestaw(List()) == List() )                        // ==> true
    println(przestaw(lista)) // List(2, 1, 4, 3, 5)
    println(przestaw(lista1)) // List(1, 2, 3, 4, 5)
    println(przestaw(lista3)) // List(2, 1, 4, 3, 6, 5)
}