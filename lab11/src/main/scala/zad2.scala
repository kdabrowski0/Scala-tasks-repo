import akka.actor.{ActorSystem, Actor, ActorLogging, ActorRef, Props}
object zad2 extends App{
    //Zmodyfikuj swoje rozwiązanie poprzedniego zadania definiując klasę
    class Gracz02 extends Actor with ActorLogging {
        override def receive: Receive = {
            case Piłeczka(maks) =>
                if(maks == 0){ 
                    println("koniec gry")
                    context.system.terminate()
                }
                else{
                    println(s"Otrzymałem pileczke ${self.path.name} i zostało $maks tur") 
                    sender() ! Piłeczka(maks - 1)
                }
            case Graj02(przeciwnik, maks) => 
                println(s"rozpoczynam gre ${self.path.name}")
                przeciwnik ! Piłeczka(maks)
            case _ => println("To nie piłeczka")
        }
    }
    val system = ActorSystem("pingPong")
    case class Graj02(przeciwnik: ActorRef, maks: Int)
    case class Piłeczka(turn: Int)
    val g1 = system.actorOf(Props[Gracz02](), "g1")
    val g2 = system.actorOf(Props[Gracz02](), "g2")

    g1 ! Graj02(g2, 10)
    //tak, aby rozgrywka składała się z zadanej liczby odbić, podanej w komunikacie typu

    //Po wykonaniu maks odbić program powinien zakończyć działanie korzystając z metody

    //context.system.terminate()
}