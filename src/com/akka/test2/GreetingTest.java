package com.akka.test2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class GreetingTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
ActorSystem system = ActorSystem.create("mySystem");
ActorRef greeter = system.actorOf(Props.create((GreetingActor.class)));
    greeter.tell(new Greeting("isuru"),greeter);
    
    }

}
