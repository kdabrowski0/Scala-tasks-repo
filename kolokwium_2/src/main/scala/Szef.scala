package kolokwium_2

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
abstract class DoSzefa
case class W(slowo: String) extends DoSzefa
case class I(slowo: String) extends DoSzefa
case class Ile(slowo: String, n: Int) extends DoSzefa

class Szef extends Actor with ActorLogging {

  def receive: Receive = {
    case W(slowo) =>
      val rslowo = slowo
        .replace("ą", "1")
        .replace("ć", "2")
        .replace("ę", "3")
        .replace("ł", "4")
        .replace("ń", "5")
        .replace("ó", "6")
        .replace("ś", "7")
        .replace("ź", "8")
        .replace("ż", "9")
        .replace("ĺ", "0")
      val pracownik = context.child(rslowo.charAt(0).toString) match {
        case Some(actorRef) => actorRef
        case None =>
          context.actorOf(Props[Pracownik](), rslowo.charAt(0).toString)
      }
      pracownik ! Wstaw(rslowo)
    case I(slowo) =>
      val rslowo = slowo
        .replace("ą", "1")
        .replace("ć", "2")
        .replace("ę", "3")
        .replace("ł", "4")
        .replace("ń", "5")
        .replace("ó", "6")
        .replace("ś", "7")
        .replace("ź", "8")
        .replace("ż", "9")
        .replace("ĺ", "0")
      val pracownik = context.child(rslowo.charAt(0).toString) match {
        case Some(pracownik) => 
          pracownik ! Szukaj(rslowo, rslowo)
        case None =>
          self ! Ile(rslowo, 0)
      }
      
    case Ile(slowo, n) =>
      val rslowo = slowo
        .replace("1", "ą")
        .replace("2", "ć")
        .replace("3", "ę")
        .replace("4", "ł")
        .replace("5", "ń")
        .replace("6", "ó")
        .replace("7", "ś")
        .replace("8", "ź")
        .replace("9", "ż")
        .replace("0", "ĺ")
      log.info(s"ilosc wystapien slowa $rslowo: $n")
  }
}

