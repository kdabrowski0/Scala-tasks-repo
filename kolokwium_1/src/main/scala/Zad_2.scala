//==========================================================================
// Metoda porównuje napisy zgodnie z polskim porządkiem alfabetycznym
// Jedyna zmiana jaka może być tutaj potrzebna to „zamiana komentarzy”
// w linijkach 9 oraz 10.
//--------------------------------------------------------------------------
def ltePL(s1: String, s2: String) = {
  import java.util.Locale
  import java.text.Collator
  // val locale = new Locale("pl", "PL") // dla starszych wersji JRE/JDK
  val locale = Locale.of("pl", "PL") // dla nowszych wersji JRE/JDK
  Collator.getInstance(locale).compare(s1, s2) <= 0
}

// Metoda nie wymaga zmian. Wczytuje dane z pliku i zwraca listę linii
def dane: List[String] = {
  import scala.io.Source
  val plik = Source.fromFile("src/main/resources/machiavelli.txt", "UTF-8")
  plik.getLines.toList
}
//==========================================================================

// Jedyna rzecz do zaimplementowania, czyli metoda „wynik”:

def wynik: List[(String, List[Int])] = {
  dane
    .zipWithIndex
    .map { case (line, lineIndex) => (line.replaceAll("[.,]", ""), lineIndex) }
    .flatMap { case (line, lineIndex) =>
      line
        .split("\\s+")
        .map(_.toLowerCase)
        .map((_, lineIndex + 1))
    }
    .groupBy(_._1)
    .map { case (word, occurrences) =>
      (word, occurrences.map(_._2).distinct.sorted)
    }
    .toList
    .sortBy(_._1)
} 

/*
  Poprawność rozwiązania należy testować (z poziomu SBT) poleceniem:
  testOnly Test2
*/

@main def zad_2: Unit = {
  // „program główny” ma znaczenie czysto pomocnicze
  if ltePL("a", "ą") then println("OK")
  else println("to nie powinno się zdarzyć…")
  println(wynik)
}

