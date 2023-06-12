
import akka.actor.{ActorSystem, Actor, ActorLogging, ActorRef, Props}

object zad4 extends App{
    class Gracz04 extends Actor with ActorLogging {
        override def receive: Receive = {
            case Piłeczka(p1, p2) =>
                println(s"Otrzymałem pileczke ${self.path.name}") 
                p1 ! Piłeczka(p2, self)
            case Graj04(przeciwnik, przeciwnik2) => 
                println(s"rozpoczynam gre ${self.path.name}")
                przeciwnik ! Piłeczka(przeciwnik2, self)
        }
    }
    val system = ActorSystem("pingPong")
    case class Graj04(przeciwnik: ActorRef, przeciwnik2: ActorRef)
    case class Piłeczka(p1: ActorRef, p2: ActorRef)
    val g1 = system.actorOf(Props[Gracz04](), "g1")
    val g2 = system.actorOf(Props[Gracz04](), "g2")
    val g3 = system.actorOf(Props[Gracz04](), "g3")

    g1 ! Graj04(g2, g3)

}