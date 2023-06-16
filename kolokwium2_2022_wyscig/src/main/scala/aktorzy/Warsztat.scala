package kolokwium2_2022_wyscig.aktorzy

object Warsztat {
    case object Cyk
    case class Awaria(auto: ActorRef)
}

class Warsztat extends Actor{
    import Warsztat._
    import Kierowca._
    import Organizator._
    def receive: Receive = {
        case Cyk =>
        
        case Awaria(auto) =>
        // Warsztat losowo ustala czy samochód uda się naprawić (z prawdopodobieństwem sukcesu 80%). Wynik naprawy zwracany będzie w postaci komunikatu typu:

        // Kierowca.scala
        //case class WynikNaprawy(efekt: Option[ActorRef])
        //Jeśli efekt zawiera wartość, to oznacza to, że naprawa się powiodła i w komunikacie znajduje się adres aktora Auto, który został naprawiony.
        //Jeśli efekt zawiera None, to oznacza to, że naprawa się nie powiodła.
        //Wysyłamy komunikat WynikNaprawy do Kierowcy, który otrzymał komunikat Awaria.
        val efekt = if(scala.util.Random.nextInt(100) < 80){
            //jesli uda sie naprawic musi odczekac losowo od 1 do 6 cykow
            val cykle = scala.util.Random.nextInt(6) + 1
            Some(auto)
        }else{
            None
        }
        sender() ! Kierowca.WynikNaprawy(efekt)

    }
    def udaSieNaprawic(): Boolean = {
        scala.util.Random.nextInt(100) < 80
    }
}