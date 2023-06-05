object zad6lab0additional extends App{
// Korzystając z rekurencji ogonowej i dopasowanie wzorca zdefiniuj generyczną funkcję:

def usun[A](l: List[A], el: A): List[A] = {
    def usunRec(l: List[A], el: A, acc: List[A]): List[A] = {
        l match {
            case Nil => acc
            case head :: tail => if(head == el) usunRec(tail, el, acc) else usunRec(tail, el, acc :+ head)
        }
    }
    usunRec(l, el, List())
}
// która „usunie” z listy l wszystkie wystąpienia elementu el.

// Przykład:

val lista = List(2, 1, 4, 1, 3, 3, 1, 2)

println(usun(lista, 1)) // ==> List(2, 4, 3, 3, 2).

}