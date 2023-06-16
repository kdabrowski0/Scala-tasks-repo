package kolokwium2_2022_wyscig.aktorzy

import akka.actor._

object Auto{
    case object Dalej
}

class Auto extends Actor{
    def receive: Receive = {
        case Dalej => 
            println(s"Auto ${self.path.name}: dalej")
            //v – aktualna prędkość (aktualizowana losowo z przedziału [0, 200] km/h, po otrzymaniu od Kierowcy sygnału „wciśnięcia gazu” Dalej). 
            //Jeśli Samochód nie uległ awarii (może ona zajść z prawdopodobieństwem 15%) to wylosowaną wartość v odsyłamy jako nową wartość prędkości do Kierowcy, 
            //wykorzystując komunikat ReakcjaAuta(Some(v)).
            //Jeśli Samochód uległ awarii to odsyłamy komunikat ReakcjaAuta(None).
            val v = scala.util.Random.nextInt(200)
            if(scala.util.Random.nextInt(100) < 15){
                sender() ! Kierowca.ReakcjaAuta(None)
            }else{
                sender() ! Kierowca.ReakcjaAuta(Some(v))
            }
    }
}