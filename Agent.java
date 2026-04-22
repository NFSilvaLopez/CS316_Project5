import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

public class Agent implements Runnable {
    //The ID of the agent
    private final int ID;
    //Feel free to modify the constructor
    public Agent(int i) {
        ID = i;
    }


    public void run() {
        int customersServed = 0;

        while (customersServed < CallCenter.CUSTOMERS_PER_AGENT) {
            try{
                CallCenter.serveQueueItems.acquire(); // wait for customer

                CallCenter.serveQueueMutex.acquire();
                int customerID = CallCenter.serveQueue.remove();
                CallCenter.serveQueueMutex.release();
                serve(customerID);
                customersServed++;
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    /*
    Your implementation must call the method below to serve each customer.
    Do not modify this method.
     */
    public void serve(int customerID) {
        System.out.println("Agent " + ID + " is serving customer " + customerID);
        try {

            // Simulate busy serving a customer by sleeping for a random period.
            sleep(ThreadLocalRandom.current().nextInt(10, 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}