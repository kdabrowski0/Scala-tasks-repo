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
    import Kierowca._
    import Warsztat._
    import Organizator._
    def receive: Receive = {
        case MaxCykow(cykle) => 
            println(s"Organizator: maksymalna liczba cykow to $cykle")
            //utwórz aktora warsztat
            val warsztat = context.actorOf(Props[Warsztat](), "warsztat")
            val dt = scala.util.Random.nextInt(10)
            //utwórz 8 aktorów typu kierowca
            val kierowcy = (0 to 10).map(index => {
                    context.actorOf(Props(new Kierowca(warsztat, dt)), s"kierowca$index")
                    }).toSet
            
            // wyslij komunikat dow szysktich kierowców o przygotowaniu auta
            kierowcy.foreach(_ ! Kierowca.PrzygotujAuto)
            context.become(wyscig(cykle,0,warsztat, kierowcy))
            
    }
    def wyscig(maxCykow: Int, currCyk: Int,warsztat: ActorRef, kierowcy: Set[ActorRef]): Receive = {
        case Cyk => 
            println(s"Organizator: cyk")
            if(maxCykow == currCyk + 1){
                context.become(koniecwyscigu())
            }else{

                context.become(wyscig(maxCykow, currCyk + 1, warsztat, kierowcy))
                //wyslij komunikat do warsztatu o cyk
                warsztat ! Warsztat.Cyk
                //wyslij komunikat do wszystkich kierowców o cyk
                kierowcy.foreach(_ ! Kierowca.Cyk)
            }
            
    }
    def koniecwyscigu(): Receive = {

    }



}