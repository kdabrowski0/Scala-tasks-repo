object zad4lab04additional extends App{
// Wykorzystując rekurencję ogonową oraz dopasowanie wzorca (nie korzystaj z metod head i tail) zdefiniuj funkcję

def maksimum(l1: List[Double], l2: List[Double]): List[Double] = {
    def maksimumRec(l1: List[Double], l2: List[Double], acc: List[Double]): List[Double] = (l1, l2) match {
        case (Nil, Nil) => acc
        case (Nil, _) => acc ++ l2
        case (_, Nil) => acc ++ l1
        case (x :: xs, y :: ys) => if (x > y) maksimumRec(xs, ys, acc :+ x) else maksimumRec(xs, ys, acc :+ y)
    }
    maksimumRec(l1, l2, List())
}
// która porównuje elementy list będących jej argumentami i „po współrzędnych” tworzy listę składającą się z maksimów. Jeżeli, któraś lista jest dłuższa, jej „nadmiarowe” elementy powinny zostać dodane na koniec listy wynikowej.

// Przykład:

val lista1 = List(2.0, -1.6, 3.2, 5.4, -8.4)
val lista2 = List(3.3, -3.1, 3.2, -4.1, -0.4, 5.5)

println(maksimum(lista1, lista2)) // ==> List(3.3, -1.6, 3.2, 5.4, -0.4, 5.5)
}