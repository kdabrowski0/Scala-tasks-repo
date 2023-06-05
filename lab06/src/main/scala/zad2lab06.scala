object zad2lab06 extends App {
// Używając poznanych na wykładzie metod przetwarzania kolekcji (groupBy ?) zdefiniuj funkcję:

    def freqMax[A](list: List[A]): (Set[A],Int) = {
        // val tuples = list.map(x => (x, list.count(_ == x)))
        // val grouped = tuples.groupBy(_._2)
        // val maxCount = grouped.keys.max
        // val maxTuples = grouped(maxCount)
        // val result = maxTuples.map(_._1).toSet
        // (result, maxCount)
        list.map(x => (x, list.count(_ == x))).groupBy(_._2).maxBy(_._1)._2.map(_._1).toSet -> list.map(x => (x, list.count(_ == x))).groupBy(_._2).maxBy(_._1)._1
    }   
// która dla list zwraca parę zawierającą zbiór elementów, których liczba wystapień w list jest maksymalna oraz – jako drugi element pary – tę liczbę.

// Przykład:

    val l = List(1, 1, 2, 4, 4, 3, 4, 1, 3)
    // assert( freqMax(l) == (Set(1,4), 3) ) // ==> true
    println(freqMax(l))
}