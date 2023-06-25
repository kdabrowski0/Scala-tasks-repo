import akka.actor.{ActorSystem, Actor, ActorLogging, ActorRef, Props}

object zad5 extends App {
  class Gracz05 extends Actor with ActorLogging {
    override def receive: Receive = {
        case Graj05(gracze, numerGracza) => gracze(numerGracza) ! Piłeczka(gracze, numerGracza)
        case Piłeczka(gracze, numerGracza) =>
            println(s"Piłeczka ${self.path.name}")
            val nowyNumerGracza = scala.util.Random.nextInt(gracze.length)
            gracze(nowyNumerGracza) ! Piłeczka(gracze, numerGracza)
    }
  }

  val system = ActorSystem("pingPong")
  case class Piłeczka(listaGraczy: List[ActorRef] ,numerGracza: Int)
  case class Graj05(listaGraczy: List[ActorRef], numerGracza: Int)
  
  println("Podaj liczbe graczy:")
  val liczbaGraczy = scala.io.StdIn.readInt()

  val listaGraczy = List.range(0, liczbaGraczy).map(numerGracza => {
    system.actorOf(Props[Gracz05](), s"gracz${numerGracza + 1}")
  })

  listaGraczy.head ! Graj05(listaGraczy, 0)
}