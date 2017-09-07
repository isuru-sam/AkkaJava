package com.akka.test.prime;

import akka.actor.UntypedActor;

public class PrimeWorker extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Throwable {
        // We only handle NumberRangeMessages
        if (message instanceof NumberRangeMessage) {
            // Cast the message to a NumberRangeMessage
            NumberRangeMessage numberRangeMessage = (NumberRangeMessage) message;
            System.out.println(
                    "Number Rage: " + numberRangeMessage.getStartNumber() + " to " + numberRangeMessage.getEndNumber());

            // Iterate over the range, compute primes, and return the list of numbers that are prime
            Result result = new Result();
            for (long l = numberRangeMessage.getStartNumber(); l <= numberRangeMessage.getEndNumber(); l++) {
                if (isPrime(l)) {
                    result.getResults().add(l);
                }
            }

            // Send a notification back to the sender
            getSender().tell(result, getSelf());
        } else {
            // Mark this message as unhandled
            unhandled(message);
        }

    }

    private boolean isPrime(long n) {
        if (n == 1 || n == 2 || n == 3) {
            return true;
        }

        // Is n an even number?
        if (n % 2 == 0) {
            return false;
        }

        // if not, then just check the odds
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
