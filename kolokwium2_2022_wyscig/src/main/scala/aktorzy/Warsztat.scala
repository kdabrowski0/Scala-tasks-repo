package kolokwium2_2022_wyscig.aktorzy

import akka.actor.{Actor, ActorRef}

object Warsztat {
  case object CykWarsztat
  case class Awaria(auto: ActorRef)
  case class NaprawaSamochodu(auto: ActorRef, cykle: Int)
}

class Warsztat extends Actor {
  import Warsztat._
  import Kierowca._

  def receive: Receive = warsztat(Map.empty)

  def warsztat(naprawianeSamochody: Map[ActorRef, Int]): Receive = {
    case CykWarsztat =>
      val updatedNaprawianeSamochody = naprawianeSamochody.map { case (auto, cykle) =>
        if (cykle == 1) {
          sender() ! WynikNaprawy(Some(auto))
          None
        } else {
          Some(auto -> (cykle - 1))
        }
      }.flatten.toMap

      context.become(warsztat(updatedNaprawianeSamochody))

    case Awaria(auto) =>
      if (!naprawianeSamochody.contains(auto)) {
        if (scala.util.Random.nextInt(100) < 80) {
          val cykle = scala.util.Random.nextInt(6) + 1
          val updatedNaprawianeSamochody = naprawianeSamochody + (auto -> cykle)
          context.become(warsztat(updatedNaprawianeSamochody))
        } else {
          sender() ! WynikNaprawy(None)
        }
      }
  }
}