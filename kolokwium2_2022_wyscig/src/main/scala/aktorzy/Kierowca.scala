package kolokwium2_2022_wyscig.aktorzy

import akka.actor._

object Kierowca {
    case object PrzygotujAuto
    case object CykKierowca
    case class ReakcjaAuta(ov: Option[Int])
    case object PodajTrasę
    case class WynikNaprawy(efekt: Option[ActorRef])
}

class Kierowca(warsztat: ActorRef, dt: Int) extends Actor{
    import Kierowca._
    import Organizator._
    import Warsztat._
    def receive: Receive = {
        case PrzygotujAuto => 
            println(s"Kierowca ${self.path.name}: przygotowuje auto")
            //utwórz aktora auto
            val auto = context.actorOf(Props[Auto](), "auto")

            context.become(wyscig(auto, 0))       
    }
    def wyscig(auto: ActorRef, dystans: Float): Receive = {
        case CykKierowca => 
            println(s"Kierowca ${self.path.name}: cyk")
            //wyslij komunikat do auta o cyk
            auto ! Auto.Dalej
        case ReakcjaAuta(ov) =>
            println(s"Kierowca ${self.path.name}: reakcja auta $ov")
            //Jeśli komunikat zawiera wartość pedkości v, Kierowca „z góry” aktualizuje przejechany dystans korzystając z danych v oraz dt 
            //s – droga przebyta do tej pory (początkowo równa 0 i aktualizowana zgodnie ze wzorem s = s + dt*v w reakcji na każdy sygnał („cyk”) „zegara” – 
            //trzeba pamiętać, że prędkość wyrażamy w km/h, a wartość dt wyrażona jest w minutach)
            //dt – stały „przyrost czasu” (wartość otrzymana od Organizatora w fazie Inicjalizacji) – wyrażony w minutach.
            //v – ostatnio zarejestrowana prędkość (otrzymana od samochodu, w reakcji na wciśnięcie gazu) – wyrażona w km/h
            //Jeśli komunikat zawiera None, Kierowca odsyła komunikat Awaria do Warsztatu.

            ov match {
                case Some(v) => 
                    println(s"Kierowca ${self.path.name}: aktualizacja dystansu")
                    //wyslij komunikat do organizatora o przejechaniu dystansu
                    val predkosc = ov.get
                    val czas = dt.toFloat / 60
                    val s = dystans + (predkosc * czas)
                    context.become(wyscig(auto, s))
                case None => 
                    println(s"Kierowca ${self.path.name}: awaria")
                    //wyslij komunikat do warsztatu o awarii
                    warsztat ! Warsztat.Awaria(auto)
                    context.become(naprawaAuta(auto, dystans))
            }
        case PodajTrasę => 
            println(s"Kierowca ${self.path.name}: koniec wyscigu")
            //wyslij komunikat do organizatora o przejechaniu dystansu
            sender() ! Organizator.TrasaPrzejechana(dystans)

        }
        def naprawaAuta(auto: ActorRef, dystans: Float): Receive = {
            case WynikNaprawy(efekt) =>
                if(efekt == None){
                    println(s"Kierowca ${self.path.name}: auto nie naprawione")
                    //wyslij komunikat do organizatora o przejechaniu dystansu
                    context.become(koniecWyscigu(auto,dystans))
                }else{
                    println(s"Kierowca ${self.path.name}: auto naprawione")
                    //wyslij komunikat do organizatora o przejechaniu dystansu
                    context.become(wyscig(auto, dystans))
                }
                
            case PodajTrasę => 
                println(s"Kierowca ${self.path.name}: koniec wyscigu")
                //wyslij komunikat do organizatora o przejechaniu dystansu
                sender() ! Organizator.TrasaPrzejechana(dystans)
        }
        def koniecWyscigu(auto: ActorRef, dystans: Float): Receive = {
            case PodajTrasę =>
                println(s"Kierowca ${self.path.name}: koniec wyscigu")
                //wyslij komunikat do organizatora o przejechaniu dystansu
                sender() ! Organizator.TrasaPrzejechana(dystans)
        }
}