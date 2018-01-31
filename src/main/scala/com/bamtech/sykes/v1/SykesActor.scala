package com.bamtech.sykes.v1

import akka.actor.{Actor, ActorLogging, Cancellable, Props}
import com.bamtech.sykes.v1.extractors.MessageExtractor
import com.bamtech.sykes.v1.generators.MessageGenerator
import org.scalacheck.Gen
import org.scalacheck.rng.Seed

object SykesActor {

  private case object GenerateMessage

  def props = Props(new SykesActor)
}

class SykesActor extends Actor with ActorLogging {

  import SykesActor._

  import scala.concurrent.duration._

  private var timerCancellable: Option[Cancellable] = None
  private val delay = 1.second

  private val parameters: Gen.Parameters = Gen.Parameters.default.withSize(1999)
  private val combinedGenerator = for {
    message <- Gen.oneOf(MessageExtractor.messages)
    generatedMessage <- MessageGenerator.message(message)
  } yield generatedMessage

  import context.dispatcher

  override def preStart(): Unit = {
    timerCancellable = Some(context.system.scheduler.schedule(delay, delay, self, GenerateMessage))
  }

  override def postStop(): Unit = {
    timerCancellable.foreach(_.cancel())
  }

  def receive: Receive = {
    case GenerateMessage =>
      combinedGenerator(parameters, Seed(System.currentTimeMillis())).foreach {
        case generatedMessage =>
          // Send to your system
          println(generatedMessage)
      }
  }

}