package com.bamtech.sykes.v1

import akka.actor.ActorSystem


object Main extends App {

  implicit val system: ActorSystem = ActorSystem(name = "sykes")

  system.actorOf(SykesActor.props)

}
