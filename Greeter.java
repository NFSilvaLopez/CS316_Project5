import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Thread.sleep;

public class Greeter implements Runnable{

    public void run() {
        int counter = 0;

        while(counter < CallCenter.NUMBER_OF_CUSTOMERS){
            try{
                CallCenter.waitQueueItems.acquire();
                CallCenter.waitQueueMutex.acquire();
                int customerID = CallCenter.waitQueue.remove();
                CallCenter.waitQueueMutex.release();

                greet(customerID);
                CallCenter.serveQueueMutex.acquire();
                CallCenter.serveQueue.add(customerID);
                int position = CallCenter.serveQueue.size();
                CallCenter.serveQueueMutex.release();
                System.out.println("Customer " + customerID +
                        " is in serve queue position:  " + position);
                CallCenter.serveQueueItems.release();

                counter++;

            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


        /*
       Your implementation must call the method below to greet each customer.
       Do not modify this method.
        */
    public void greet(int customerID) {
        System.out.println("Greeting customer " + customerID);
        try {
            // Simulate busy greeting a customer by sleeping for a random period.
            sleep(ThreadLocalRandom.current().nextInt(10, 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}