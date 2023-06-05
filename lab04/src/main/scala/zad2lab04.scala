import scala.annotation.tailrec
// Stosując rekurencję ogonową (i nie używając zmiennych) zdefiniuj funkcję:

object  zad2lab04 extends App{
def tasuj(l1: List[Int], l2: List[Int]): List[Int] = {
  @tailrec
  def tasujHelper(l1: List[Int], l2: List[Int], acc: List[Int]): List[Int] = (l1, l2) match {
    case (Nil, Nil) => acc
    case (Nil, h2 :: t2) => tasujHelper(Nil, t2, h2 :: acc)
    case (h1 :: t1, Nil) => tasujHelper(t1, Nil, h1 :: acc)
    case (h1 :: t1, h2 :: t2) =>
      if (h1 <= h2) tasujHelper(t1, l2, h1 :: acc)
      else tasujHelper(l1, t2, h2 :: acc)
  }

  tasujHelper(l1, l2, Nil).reverse
}
    val lista1 = List(2, 4, 3, 5)
    val lista2 = List(1, 2, 2, 3, 1, 5)

    println(tasuj(lista1, lista2) == List(1, 2, 3, 1, 4, 3, 5)) // true
}
// łączącą ze sobą listy liczb całkowitych z zachowaniem porządku rosnącego (a ściślej „niemalejącego”). W szczególności oznacza to, że jeśli l1 i l2 będą uporządkowane to wynik również będzie uporządkowany. W wyniku „kolejno” nie powinny pojawiać się identyczne elementy.

// Przykład:


// W definicji wykorzystaj mechanizm dopasowania wzorca. Dla przypomnienia, przykład z materiałów z ostatniego wykładu, który, co prawda, dodatkowo obsługuje „dowolne listy” (czyli List[A], gdzie „A” jest parametrem):

// PRZYKŁAD: Korzystając z rekurencji ogonowej zdefiniujmy sobie
// funkcję obliczającą długość dodwolnej listy:
// def długość[A](lista: List[A], dł: Int = 0): Int = lista match {
//   case _ :: ogon => długość(ogon, dł + 1)
//   case _ => dł
// }
// Uwaga: Symbol _ we wzorcu _ :: ogon oznacza, że nie interesuje nas czym jest „głowa listy” (bo i tak nie zamierzamy z tego korzystać), ważne jedynie, że lista jest niepusta (bo ma „głowę” i „ogon”). Gdybyśmy chcieli do czegoś użyć wartość „głowy” to moglibyśmy to zrobić np. tak:

// def długość2[A](lista: List[A], dł: Int = 0): Int = lista match {
//   case głowa :: ogon =>
//     println(s"„Głową” listy $lista jest $głowa")
//     długość(ogon, dł + 1)
//   case _ => dł
// }