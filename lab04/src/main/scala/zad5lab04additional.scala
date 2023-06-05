object zad5lab04additional extends App{
// Wykorzystując rekurencję ogonową oraz dopasowanie wzorca zdefiniuj generyczną funkcję:

def divide[A](l: List[A]): (List[A], List[A]) = {
    def divideRec(l: List[A], acc1: List[A], acc2: List[A]): (List[A], List[A]) = {
        l match {
            case Nil => (acc1, acc2)
            case x :: y :: tail => divideRec(tail, acc1 :+ x, acc2 :+ y)
            case x :: Nil => divideRec(Nil, acc1 :+ x, acc2)
        }
    }
    divideRec(l, List(), List())
}
// która dla listy l będącej argumentem zwraca parę (l1, l2) jej „podlist” składających się, odpowiednio, ze wszystkich elementów l o parzystych oraz nieparzystych indeksach.

// Przykład:

val lista = List(1, 3, 5, 6, 7)

println(divide(lista)) // ==>  ( List(1, 5, 7), List(3, 6) ).
}