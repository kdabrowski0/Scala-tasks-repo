// Zdefiniuj funkcję


object zad1lab04 extends App {
    def ciąg(n: Int): Int = {
        def ciagTailrec(i: Int, last: Int, nextToLast: Int): Int = {
            if (i >= n) last
            else ciagTailrec(i + 1, last + nextToLast, last)
        }
        if (n == 0) 2
        else if(n <= 1) 1
        else ciagTailrec(1, 1, 2)
 }
    println(ciąg(0))
    println(ciąg(1))
    println(ciąg(2))
    println(ciąg(3))
    println(ciąg(4))
    println(ciąg(5))
}

// zwracającą dla podanej wartości argumentu n, n-ty element ciągu C, wyrażonego wzorem:

// C(0) == 2
// C(1) == 1
// C(n) == C(n - 1) + C(n - 2) dla n > 1
// Pierwsze 10 wyrazów ciągu to: 2, 1, 3, 4, 7, 11, 18, 29, 47, 76.

// Rozwiąż to zadanie bez używania zmiennych oraz wykorzystując rekurencję ogonową.