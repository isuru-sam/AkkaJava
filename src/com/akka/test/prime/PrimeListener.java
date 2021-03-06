package com.akka.test.prime;

import akka.actor.UntypedActor;

public class PrimeListener extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Result) {
            Result result = (Result) message;

            System.out.println("Results: ");
            for (Long value : result.getResults()) {
                System.out.print(value + ", ");
            }
            System.out.println();

            // Exit
            getContext().system().shutdown();
        } else {
            unhandled(message);
        }
    }
}
