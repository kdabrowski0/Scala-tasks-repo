package lab10

sealed trait Frm
case object False extends Frm
case object True extends Frm
case class Not(f: Frm) extends Frm
case class And(f1: Frm, f2: Frm) extends Frm
case class Or(f1: Frm,f2: Frm) extends Frm
case class Imp(f1: Frm, f2: Frm) extends Frm

val frm = Imp(Or(True, False), Not(And(True, False)))

// UWAGA: W rozwiązaniach poniższych zadań (tam gdzie to możliwe)
//        wykorzystaj rekurencję ogonową.

@main def zad_1: Unit = {
  // Ćwiczenie 1: zaimplementuj toString tak, aby „minimalizowało”
  //              liczbę nawiasów
  println(frm)
  // Powinno wyprodukować coś „w stylu”:
  // (True | False) -> !(True & False)
}

@main def zad_2: Unit = {
  // Ćwiczenie 2: zaimplementuj funkcję wyliczającą wartość logiczną
  //              formuły.
  def eval(f: Frm): Boolean = ???
  // eval(False) == false
  // eval(frm) == true
}

@main def zad_3: Unit = {
  // Ćwiczenie 3: napisz funkcję wyliczającą dla danej formuły f
  //              zbiór jej wszystkich „podformuł”
  def closure(f: Frm): Set[Frm] = ???
  // val frm = Imp(Or(True, False), Not(And(True, False)))
  //
  // np. dla formuły frm powyżej wynikiem powinien być zbiór:
  // 
  // { True, False, True | False, True & False, !(True & False), frm }
  //
  // Jak widać, closure(frm) zawiera również formułę będącą argumentem.
}