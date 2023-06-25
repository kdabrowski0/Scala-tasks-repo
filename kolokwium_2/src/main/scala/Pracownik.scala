package kolokwium_2

import akka.actor.{Actor, ActorLogging, Props}

abstract class DoPracownika
case class Wstaw(slowo: String) extends DoPracownika
case class Szukaj(slowo: String, prevslowo: String) extends DoPracownika

class Pracownik extends Actor with ActorLogging {
  def receive: Receive = praca(0)

  def praca(licznik: Int): Receive = {
    case Wstaw(slowo) =>
      if (slowo.length == 1) {
        context.become(praca(licznik + 1))
      } else {
        val nazwaPracownika = slowo.charAt(1).toString
        val pracownik = context.child(nazwaPracownika) match {
          case Some(actorRef) => actorRef
          case None =>
            context.actorOf(Props[Pracownik](), nazwaPracownika)
        }
        pracownik ! Wstaw(slowo.substring(1))
      }
    case Szukaj(slowo, prevslowo) =>
      if (prevslowo.length == 1) {
        context.parent ! Ile(slowo, licznik)
        // val szefSelection = context.actorSelection("/user/szef") !!! SPOSÓB 2
        // szefSelection ! Ile(slowo, licznik) !!! SPOSÓB 2
      } else {
        val nazwaPracownika = prevslowo.charAt(1).toString
        val pracownik = context.child(nazwaPracownika) match {
          case Some(pracownik) => 
            pracownik ! Szukaj(slowo,prevslowo.substring(1))
          case None =>
            context.parent ! Ile(slowo, 0)
            // val szefSelection = context.actorSelection("/user/szef") !!! SPOSÓB 2
            // szefSelection ! Ile(slowo, 0) !!! SPOSÓB 2
        }
      }
    // case I(slowo) =>
    //   if(slowo.length == 1){
    //     context.parent ! Ile(slowo, licznik)
    //   }else{
    //     val nazwaPracownika = slowo.charAt(1).toString
    //     val pracownik = context.child(nazwaPracownika) match {
    //       case Some(pracownik) => 
    //         pracownik ! I(slowo.substring(1))
    //       case None =>
    //         context.parent ! Ile(slowo, 0)
    //     }
    //   }
    case Ile(slowo, n) =>
      // val rslowo = context.self.path.name + slowo // !!! SPOSÓB 3
      // context.parent ! Ile(rslowo, n) // !!! SPOSÓB 3
      context.parent ! Ile(slowo, n) //!!! SPOSÓB 1
      // val szefSelection = context.actorSelection("/user/szef") !!! SPOSÓB 2
      // szefSelection ! Ile(slowo, n) !!! SPOSÓB 2
  }    
}