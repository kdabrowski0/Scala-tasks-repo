object zad4lab05additional extends App{
// Korzystając z rekurencji ogonowej zdefiniuj generyczną funkcję:

def oczyść[A](l: List[A]): List[A] = {
    def oczyśćRec(l: List[A], acc: List[A]): List[A] = {
        l match {
            case Nil => acc
            case x :: xs => if(acc.isEmpty || acc.last != x) oczyśćRec(xs, acc :+ x) else oczyśćRec(xs, acc)
        }
    }
    oczyśćRec(l, List())
}
// która w liście l zamienia wszystkie podciągi powtarzających się elementów ich pojedynczymi wystąpieniami. Zdefiniuj funkcję korzystając ze wzorców. Nie używaj metod head i tail.

// Przykład:

val lista = List(1, 1, 2, 4, 4, 4, 1, 3)
println(oczyść(lista)) // ==> List(1, 2, 4, 1, 3)

}