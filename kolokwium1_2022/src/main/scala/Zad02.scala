/*
  UWAGA! Uzupełnij kod funkcji „group” zgodnie z treścią zadania.
         Z poziomu SBT wydaj polecenie „test” żeby sprawdzić, czy
         Twoje rozwiązanie przechodzi przygotowane testy jednostkowe.
         Możesz dzięki temu nie tworzyć „programu głównego”.
*/

def group[A](list: List[A])(len: Int, shift: Int = 1): List[List[A]] = {
  require(len > 0)
  require(shift > 0)

  @annotation.tailrec
  def length(list: List[A], len: Int = 0): Int = list match {
    case Nil => len
    case _ :: tail => length(tail, len + 1)
  }

  @annotation.tailrec
  def take(list: List[A], amount: Int, acc: List[A] = List()): List[A] = list match
    case Nil => acc.reverse
    case _ if amount == 0 => acc.reverse
    case head :: tail => take(tail, amount - 1, head :: acc)


  @annotation.tailrec
  def drop(list: List[A], amount: Int): List[A] = list match
    case Nil => Nil
    case _ if amount == 0 => list
    case _ :: tail => drop(tail, amount - 1)

  @annotation.tailrec
  def groupHelper(list: List[A])(len: Int, shift: Int, acc: List[List[A]] = List()): List[List[A]] = list match {
    case Nil => acc.reverse
    case _ if length(list) <= len && (acc != List()) => (take(list, len) :: acc).reverse
    case _ if length(list) < len => List(list)
    case _ => groupHelper(drop(list, shift))(len, shift, take(list, len) :: acc)
  }

  groupHelper(list)(len, shift)
}

/*
  SUGESTIA. Być może ułatwisz sobie rozwiązanie zadania, jeśli „wewnątrz”
    funkcji „group” zdefiniujesz pewną liczbę funkcji pomocniczych. Pamiętaj,
    że jeśli będą one używały rekurencji to musi ona być „ogonowa“.
*/
