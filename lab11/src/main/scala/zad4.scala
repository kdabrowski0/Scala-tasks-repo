import akka.actor.{ActorSystem, Actor, ActorLogging, ActorRef, Props}

object zad4 extends App {
  class Gracz04 extends Actor with ActorLogging {
    override def receive: Receive = {
        case Graj04(gracze, numerGracza) => gracze(numerGracza) ! Piłeczka(gracze, numerGracza)
        case Piłeczka(gracze, numerGracza) =>
            println(s"Piłeczka ${self.path.name}")
            if (numerGracza + 1 > gracze.length - 1) gracze.head ! Piłeczka(gracze, 0)
            else gracze(numerGracza + 1) ! Piłeczka(gracze, numerGracza + 1)
    
    }
  }

  val system = ActorSystem("pingPong")
  case class Piłeczka(listaGraczy: List[ActorRef] ,numerGracza: Int)
  case class Graj04(listaGraczy: List[ActorRef], numerGracza: Int)
  
  println("Podaj liczbe graczy:")
  val liczbaGraczy = scala.io.StdIn.readInt()

  val listaGraczy = List.range(0, liczbaGraczy).map(numerGracza => {
    system.actorOf(Props[Gracz04](), s"gracz${numerGracza + 1}")
  })

  listaGraczy.head ! Graj04(listaGraczy, 0)
}







