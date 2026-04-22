import java.util.concurrent.Semaphore;

public class Customer implements Runnable {
        //The ID of the customer.
        private final int ID;
        //Feel free to modify the constructor
        public Customer(int i) {
            ID = i;
        }


    public void run() {
        try {
            CallCenter.waitQueueMutex.acquire();
            CallCenter.waitQueue.add(ID);
            System.out.println("Customer " + ID + " has entered waiting queue");
            CallCenter.waitQueueMutex.release();

            CallCenter.waitQueueItems.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
