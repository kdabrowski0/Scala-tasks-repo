object zad5lab05additional extends App{
// Korzystając z rekurencji ogonowej zdefiniuj generyczną funkcję:

def skompresuj[A](l: List[A]): List[(A, Int)] = {
    def skompresujRec(l: List[A], acc: List[(A, Int)]): List[(A, Int)] = {
        l match {
            case Nil => acc
            case x :: xs => if(acc.isEmpty || acc.last._1 != x) skompresujRec(xs, acc :+ (x, 1)) else skompresujRec(xs, acc.init :+ (acc.last._1, acc.last._2 + 1)) 
            // init return all elements except the last one
        }
    }
    skompresujRec(l, List())
}
// która zastępuje każdy „podciąg” powtarzających się wystąpień elementu el na liście l parą (el, długość_podciągu). Nie używaj metod head i tail.

// Przykład:

val lista = List('a', 'a', 'b', 'c', 'c', 'c', 'a', 'a', 'b', 'd')
println(skompresuj(lista)) // ==> List(('a', 2), ('b', 1), ('c', 3), ('a', 2), ('b', 1), ('d', 1))
}