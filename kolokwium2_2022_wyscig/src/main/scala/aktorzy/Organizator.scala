package kolokwium2_2022_wyscig.aktorzy

import akka.actor.*

object Organizator{
    case object Cyk
    case class MaxCykow(cykle: Int){
        assert(cykle > 0)
    }
    case class TrasaPrzejechana(iloscKm: float)
}

class Organizator extends Actor{
    def receive = {
        case MaxCykow(cykle) => 
            println(s"Organizator: maksymalna liczba cykow to $cykle")
    }



}