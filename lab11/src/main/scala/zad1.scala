
import akka.actor.{ActorSystem, Actor, ActorLogging, ActorRef, Props}

//Zdefiniuj klasę
object zad1 extends App{
    class Gracz01 extends Actor with ActorLogging {
        override def receive: Receive = {
            case Piłeczka(message) =>
                println(s"Otrzymałem pileczke ${self.path.name}") 
                sender() ! Piłeczka("Pong")
            case Graj01(przeciwnik) => przeciwnik ! Piłeczka("Ping")
            case _ => println("To nie piłeczka")
        }
    }
    val system = ActorSystem("pingPong")
    case class Graj01(przeciwnik: ActorRef)
    case class Piłeczka(message: String)
    val g1 = system.actorOf(Props[Gracz01](), "g1")
    val g2 = system.actorOf(Props[Gracz01](), "g2")

    g1 ! Graj01(g2)
    //która, z poziomu programu głównego, posłuży do utworzenia dwóch aktorów, rozgrywających między sobą partię „ping-ponga”. 
    //Jako „wirtualnej piłeczki” użyj komunikatu

    //Ponieważ któryś z graczy musi rozpocząć grę, w programie głównym (po utworzeniu obu graczys) 
    //prześlij do jednego z nich komunikat typu

    //zawierający referencję do jego przeciwnika. Pamiętaj, że „tożsamość” aktora można zmieniać używając konstrukcji

    //context.become(...)

}
