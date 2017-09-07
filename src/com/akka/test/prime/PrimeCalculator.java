package com.akka.test.prime;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class PrimeCalculator {
    public void calculate(long startNumber, long endNumber) {
        // Create our ActorSystem, which owns and configures the classes
        ActorSystem actorSystem = ActorSystem.create("primeCalculator");

        // Create our listener
        final ActorRef primeListener = actorSystem.actorOf(Props.create(PrimeListener.class), "primeListener");

        // Create the PrimeMaster: we need to define an UntypedActorFactory so that we can control
        // how PrimeMaster instances are created (pass in the number of workers and listener reference
        ActorRef primeMaster = actorSystem.actorOf(Props.create(PrimeMaster.class, 10, primeListener));

        // Start the calculation
        primeMaster.tell(new NumberRangeMessage(startNumber, endNumber), primeMaster);
    }

    public static void main(String[] args) {
        /*
         * if( args.length < 2 ) { System.out.println(
         * "Usage: java com.geekcap.akka.prime.PrimeCalculator <start-number> <end-number>" ); System.exit( 0 ); }
         */
//http://www.javaworld.com/article/2078775/scripting-jvm-languages/open-source-java-projects-akka.html?page=6
        long startNumber = 1;
        long endNumber = 100;

        PrimeCalculator primeCalculator = new PrimeCalculator();
        primeCalculator.calculate(startNumber, endNumber);
    }

}
