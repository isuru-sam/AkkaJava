package com.akka.test2;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class GreetingActor extends UntypedActor {
LoggingAdapter log = Logging.getLogger(getContext().system(), this); 
    @Override
    public void onReceive(Object message) throws Exception {
if(message instanceof Greeting)
{
 log.info("Hello" + ((Greeting)message).who);   
 getSender().tell("Hi"+((Greeting)message).who, getSelf());
}
    
    }

}
