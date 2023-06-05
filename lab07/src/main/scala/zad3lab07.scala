
// Napisz program, który oblicza wyniki zawodów sportowych w konkurencji, w której zawodnicy oceniani są w dwóch kategoriach:

// wdzięk
// spryt
// Ocena „cząstkowa” ma postać:

case class Ocena(imię: String, nazwisko: String, wdzięk: Int, spryt: Int) {
  require(
    imię.trim() != "" &&
    nazwisko.trim() != "" &&
    (0 to 20).contains(wdzięk) &&
    (0 to 20).contains(spryt)
  )
}

case class Wynik(
  miejsce: Int,
  imię: String,
  nazwisko: String,
  średniWdzięk: Double,
  średniSpryt: Double,
  suma: Double
) {
  require(
    miejsce >= 0 &&
    imię.trim() != "" &&
    nazwisko.trim() != "" &&
    średniWdzięk >= 0 && średniWdzięk <= 20 &&
    średniSpryt >= 0 && średniSpryt <= 20 &&
    suma == średniWdzięk + średniSpryt
  )
}

object Main{
    def main(args: Array[String]): Unit = {
    val oceny = List(
      Ocena("Jan", "Kowalski", 19, 17),
      Ocena("Jan", "Kowalski", 18, 18),
      Ocena("Anna", "Nowak", 20, 19),
      Ocena("Anna", "Nowak", 16, 20),
      Ocena("Krzysztof", "Kowalski", 15, 18),
      Ocena("Krzysztof", "Kowalski", 17, 16),
      Ocena("Barbara", "Jankowska", 18, 20),
      Ocena("Barbara", "Jankowska", 19, 16),
      Ocena("Arek", "Ailk", 18, 20),
      Ocena("Arek", "Ailk", 19, 16)
    )

    val wynik = oceny.groupBy(x => (x.imię , x.nazwisko))
        .mapValues(oceny => {
        val n = oceny.length
        val sumaW = oceny.map(_.wdzięk).sum.toDouble / n
        val sumaS = oceny.map(_.spryt).sum.toDouble / n
        val suma = sumaW + sumaS
        (sumaW, sumaS, suma)
        })
        .toList
        .sortBy { case ((imie, nazwisko), (sumaW, sumaS, suma)) => (-suma, -sumaW, nazwisko, imie) } // minus przy suma i sumaW oznacza sortowanie malejąco
        .zipWithIndex
        .map { case (((imie, nazwisko), (sumaW, sumaS, suma)), i) => Wynik(i+1, imie, nazwisko, sumaW, sumaS, suma) }

    wynik.foreach(w => println(s"${w.miejsce}. ${w.imię} ${w.nazwisko}: wdziek=${w.średniWdzięk}, spryt=${w.średniSpryt}, suma=${w.suma}")) // dla czytelności 

    // println(wynik)

        
    }
// Przykład
// Ocena("Jan", "Kowalski", 19, 17) // ocena „cząstkowa” dla Jana Kowalskiego
// Ocena("Jan", "Kowalski", 18, 18) // kolejna ocena „cząstkowa” dla Jana Kowalskiego
// ...
// Załóż, że:

// zawodnicy identyfikowani są poprzez imię i nazwisko
// każdy zawodnik może otrzymać dowolną liczbę ocen „cząstkowych”
// oceny wdzięk oraz spryt są dowolnymi liczbami całkowitymi z zakresu [0..20].
// Ostateczny wynik zawodnika jest to para liczb typu Double będących średnimi arytmetycznymi ocen cząstkowych w podanych powyżej „kategoriach”.

// „Ranking” ustala się sumując obie „średnie” noty każdego z zawodników - wyższa suma oznacza lepszy wynik.

// Jeśli sumy not dwóch zawodników są identyczne, to wyższe miejsce zajmuje ten, którego (średnia) nota za wdzięk była wyższa. Jeśli również noty za wdzięk są identyczne, to zawodnicy zajmuja miejsca ex-aequo.

// Załóż, że dane wejściowe programu stanowi lista obiektów reprezentujących oceny „cząstkowe”. Program powinien stworzyć uporządkowaną listę obiektów reprezentujących informacje o:

// miejscu zajętym przez zawodnika
// imieniu i nazwisku zawodnika
// uzyskanym wyniku
// W przypadku miejsc ex-aequo kolejność na liście wynikowej powinna być zgodna z porządkiem alfabetycznym nazwisk zawodników.

// W rozwiązaniu możesz wykorzystywać dowolne niemutowalne kolekcje języka Scala i wszelkie dostępne dla nich metody standardowe.
}