object zad2lab07 extends App {
// Korzystając z metod oferowanych przez kolekcje zdefiniuj funkcję:

def indeksy[A](lista: List[A], el: A): Set[Int] = {
    lista.zipWithIndex.filter(_._1 == el).map(_._2).toSet
}
// zwracającą wszystkie indeksy w liście lista, na których znajduje się element el.

// Przykłady:

val lista = List(1, 2, 1, 1, 5)
assert( indeksy(lista, 1) == Set(0, 2, 3) ) // ==> true
assert( indeksy(lista, 7) == Set() )        // ==> true
println(indeksy(lista, 1))
println(indeksy(lista, 7))
    
}