package kolokwium2_2022_wyscig.aktorzy

import akka.actor._

object Organizator {
  case object Cyk
  case class MaxCykow(cykle: Int) {
    assert(cykle > 0)
  }
  case class TrasaPrzejechana(iloscKm: Float)
  case object PobierzWyniki
  case class Wyniki(wyniki: Map[ActorRef, Float])
}

class Organizator extends Actor {
  import Kierowca._
  import Warsztat._
  import Organizator._
  import Ordering.Float.IeeeOrdering

  def receive: Receive = {
    case MaxCykow(cykle) =>
      println(s"Organizator: maksymalna liczba cykow to $cykle")
      val warsztat = context.actorOf(Props[Warsztat](), "warsztat")
      val dt = scala.util.Random.nextInt(10)
      val kierowcy = (0 to 10).map(index => {
        context.actorOf(Props(new Kierowca(warsztat, dt)), s"kierowca$index")
      }).toSet
      kierowcy.foreach(_ ! Kierowca.PrzygotujAuto)
      context.become(wyscig(cykle, 0, warsztat, kierowcy, Map.empty))
  }

  def wyscig(maxCykow: Int, currCyk: Int, warsztat: ActorRef, kierowcy: Set[ActorRef], wyniki: Map[ActorRef, Float]): Receive = {
    case Cyk =>
      println(s"Organizator: cyk")
      if (maxCykow == currCyk + 1) {
        context.become(koniecwyscigu(kierowcy, wyniki))
      } else {
        context.become(wyscig(maxCykow, currCyk + 1, warsztat, kierowcy, wyniki))
        warsztat ! Warsztat.CykWarsztat
        kierowcy.foreach(_ ! Kierowca.CykKierowca)
      }

    case TrasaPrzejechana(iloscKm) =>
      println(s"${sender()}: przejechał trasę $iloscKm km")
      val updatedWyniki = wyniki + (sender() -> iloscKm)
      context.become(wyscig(maxCykow, currCyk, warsztat, kierowcy, updatedWyniki))
  }

    def koniecwyscigu(kierowcy: Set[ActorRef], wyniki: Map[ActorRef, Float]): Receive = {
        case Cyk =>
            println("Organizator: koniec wyscigu")
            kierowcy.foreach(_ ! Kierowca.PodajTrasę)
        case TrasaPrzejechana(iloscKm) =>
            println(s"${sender()}: przejechał trasę $iloscKm km")
            val updatedWyniki = wyniki + (sender() -> iloscKm)
            context.become(koniecwyscigu(kierowcy, updatedWyniki))
            self ! PobierzWyniki
        case PobierzWyniki =>
            println("Organizator: pobieranie wynikow")
            val posortowaneWyniki = wyniki.toList.sortBy(-_._2) // Sortowanie malejąco
            posortowaneWyniki.zipWithIndex.foreach { case ((kierowca, dystans), miejsce) =>
            val miejsceTekst = if (miejsce == 0) "1. miejsce" else if (miejsce == 1) "2. miejsce" else if (miejsce == 2) "3. miejsce" else s"${miejsce + 1}. miejsce"
            println(s"$miejsceTekst: ${kierowca.path.name}: $dystans km")
            context.system.terminate()
        }
       
    }
}