// Stosując rekurencję ogonową zdefiniuj generyczną funkcję

object zad3lab06 extends App {


def difficult[A](list: List[A])(len: Int, shift: Int = 1): List[List[A]] = {
  require(len > 0)
  require(shift > 0)

  @annotation.tailrec
  def lengthh(list: List[A], len: Int = 0): Int = {
   list match {
    case Nil => len
    case _ :: tail => lengthh(tail, len + 1)
   }
  }

  @annotation.tailrec
  def takee(list: List[A], amount: Int, acc: List[A] = List()): List[A] ={
    list match{
      case Nil => acc.reverse
      case _ if amount == 0 => acc.reverse
      case head :: tail => takee(tail, amount - 1, head :: acc)
    }
  }

  @annotation.tailrec
  def dropp(list: List[A], amount: Int): List[A] = {
    list match{
      case Nil => Nil
      case _ if amount == 0 => list
      case _ :: tail => dropp(tail, amount - 1)
    }
  }

  @annotation.tailrec 
  def difficultTail(list: List[A])( len: Int, shift: Int, acc: List[List[A]] = List() ): List[List[A]] = {
    list match {
      case Nil => acc.reverse
      case lista if lengthh(lista) <= len => difficultTail(dropp(lista, lengthh(lista)))(len, shift, lista :: acc)
      case lista  =>
        val sublist = takee(lista, len)
        difficultTail(dropp(lista, shift))(len, shift, sublist :: acc)
    }
  }
  difficultTail(list)(len, shift)
}
// która przekształca argument list grupując jego elementy w „podlisty” o (maksymalnej) długości len, stosując przy tym „przesunięcie” określone przez wartość parametru shift.

// Przykład 1:

val ( listaa, lenn, shiftt ) = ( List(1,2,3,4,5), 3, 1 )
println(difficult(listaa)(lenn, shiftt)) // List(List(1,2,3), List(2,3,4), List(3,4,5)) // => true
// Przykład 2:

val ( list, len, shift ) = ( List(1,2,3,4,5), 2, 2 )
println(difficult(list)(len, shift)) //List(List(1,2), List(3,4), List(5))         // => true

}
// W definicji wykorzystaj dopasowanie wzorca (pattern matching). Nie używaj: zmiennych, „pętli” (foreach, while), wyliczenia for/yield, ani gotowych metod operujących na kolekcjach. Jeśli okaże się to potrzebne/pomocne, możesz skorzystać z metody reverse oraz operacji dołączania elementu na początek listy (el :: lista)