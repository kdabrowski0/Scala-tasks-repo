object zad1lab06 extends App{
    // Korzystając z „kolekcyjnych” metod drop i take zdefiniuj generyczną funkcję:

    def subseq[A](list: List[A], begIdx: Int, endIdx: Int): List[A] = {
        list.drop(begIdx).take(endIdx)
    }

// zwracającą podciąg listy list, złożony z elementów o indeksach z przedziału od begIdx do endIdx.

// W swoim rozwiązniu nie korzystaj z rekurencji!

// Dla dowolnej listy l: List[A]

// l.take: Int => List[A]
// l.drop: Int => List[A]
// Przykład:

    val l = List(1,2,3,4,5)
    println(subseq(l, 1, 4))  
    println(subseq(l, 0, 0))  
    println(subseq(l, 1, 3)) 
    println(subseq(l, 10, 20)) 
    // println(l.take(1))  // List(1)
    // println(l.take(3))  // List(1,2,3)
    // println(l.take(0))  // List()
    // println(l.take(10)) // l

    // println(l.drop(1))  // List(2,3,4,5)
    // println(l.drop(3))  // List(4,5)
    // println(l.drop(0))  // l
    // println(l.drop(10)) // List()

}