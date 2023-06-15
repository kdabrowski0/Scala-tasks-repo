import akka.actor.ActorLogging
import akka.actor.{Actor, PoisonPill}
import akka.actor.Actor.Receive
import akka.actor.Props
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Terminated
import scala.concurrent.duration._
import concurrent.ExecutionContext.Implicits.global
import javax.swing.text.Position
import scala.util.Random


object lab14 extends App{

  object Zamek{
    case object ZStart
    case object Walcz
    case class Umarl(obronca: ActorRef)
  }
  class Zamek extends Actor with ActorLogging {
    import Zamek._
    import Obronca._
    def receive: Receive = {
      case ZStart => 
        // create 100 defenders
        val defenders = (1 to 100).map { i =>
          context.actorOf(Props[Obronca](), s"Obronca$i")
        }.toList

        context.become(walka(defenders))
      
    }
    def walka(obrońcy: List[ActorRef]): Receive = {
      case Walcz =>
        if(obrońcy.isEmpty){
            println(s"Zamek ${self.path.name} został zdobyty")
            context.system.terminate()
            System.exit(0)
        }else{
            println(s"Zamek ${self.path.name} broni się i posiadamy ${obrońcy.length} obrońców")
            obrońcy.foreach(_ ! Obronca.Strzel(obrońcy.length))
        }
      case Umarl(obronca) =>
        println(s"Zamek ${self.path.name} stracił obrońcę ${obronca.path.name}")
        context.become(walka(obrońcy.filter(_ != obronca)))
      
    }

  }
  object Obronca {
    case class Strzel(iloscObroncow: Int)
    
  }
  class Obronca extends Actor with ActorLogging {
    import Obronca._
    def receive: Receive = {
      case Strzel(iloscObroncow) =>
        println(s"Obronca ${self.path.name} strzela")
        val randomNumberGenerator = scala.util.Random
        val szansaNaŚmierć = if (iloscObroncow > 1) 0.5 else 0.9
        if (randomNumberGenerator.nextDouble() > szansaNaŚmierć) {
          println(s"Obronca ${self.path.name} ginie")
          context.parent ! Zamek.Umarl(self)
          self ! PoisonPill
        }

    }
  }
  object SilaWyzsza {
    case object Strzał
    case object Start
  }
  class SilaWyzsza extends Actor with ActorLogging {
    import SilaWyzsza._
    import Zamek._
    def receive: Receive = {
      case Start => 
          val zamekA = context.actorOf(Props[Zamek](), "ZamekA")
          val zamekB = context.actorOf(Props[Zamek](), "ZamekB")

          zamekA ! Zamek.ZStart
          zamekB ! Zamek.ZStart

          context.become(walka(zamekA,zamekB))

    }
    def walka(zamek1: ActorRef, zamek2: ActorRef): Receive = {
      case Strzał =>
          val castle1 = zamek1
          val castle2 = zamek2

          castle1 ! Zamek.Walcz
          castle2 ! Zamek.Walcz
    }
  }
    
  // Create the ActorSystem first
  val system = ActorSystem("system")
  // Move the import statement for global execution context here
  import system.dispatcher

  val silaWyzsza = system.actorOf(Props[SilaWyzsza](), "SilaWyzsza")

  silaWyzsza ! SilaWyzsza.Start

  // Schedule the task with corrected arguments
  system.scheduler.scheduleWithFixedDelay(
    Duration.Zero,
    1000.millis,
    silaWyzsza,
    SilaWyzsza.Strzał
  )

}