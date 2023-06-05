object zad4lab06additional extends App{
// Korzystając z metod filter i partition zdefiniuj funkcję:

def pairPosNeg(list: List[Double]): (List[Double], List[Double]) = {
    list.filter(_ != 0).partition(x => {x < 0})
}
val lista = List(2.0, 0.0, 1.0, -1.0, 0.0, -5.0, 4.0, -10.0)
println(pairPosNeg(lista))
// która podzieli listę list na parę list (negative, positive), zawierających odpowiednio - wszystkie ujemne oraz wszystkie dodatnie elementy z listy list. 
// W wyniki, elementy powinny występować w tej samej kolejności oraz krotności jak na liście list. Liczby równe 0.0 powinny zostać pominięte
}