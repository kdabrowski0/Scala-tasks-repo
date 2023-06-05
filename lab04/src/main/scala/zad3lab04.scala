import scala.annotation.tailrec
// Stosując rekurencję ogonową oraz mechanizm dopasowania wzorca zdefiniuj funkcję:

object zad3lab04 extends App {
    def sumuj(l: List[Option[Double]]): Option[Double] = {
        @tailrec
        def sumujTail(lista: List[Option[Double]], acc: Double): Option[Double] = {
            lista match {
                case Nil => if (acc == 0) None else Some(acc)
                case None :: tail => sumujTail(lista = tail, acc = acc)
                case Some(head) :: tail if head < 0.0 => sumujTail(lista = tail, acc = acc)
                case Some(head) :: tail => sumujTail(lista = tail, acc = acc + head)
            }
        }
        sumujTail(l, 0)
    }
    val lista = List(Some(4.0),None, Some(-3.0) ,Some(1.0), Some(0.0))
    val lista2 = List(Some(-3.0), None)
    println(sumuj(lista) == Some(5.0)) // true
    println(sumuj(lista))
    println(sumuj(lista2))
}
// zwracającą sumę wszystkich elementów listy l postaci Some(d), gdzie d > 0. Jeśli takich elementów na liście l nie ma, to funkcja powinna zwrócić wartość None.

// Przykład:

