import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Thread.sleep;

public class CallCenter {
    static Semaphore waitQueueItems = new Semaphore(0);
    static Semaphore serveQueueItems = new Semaphore(0);
    static Semaphore waitQueueMutex = new Semaphore(1);
    static Semaphore serveQueueMutex = new Semaphore(1);
    static Queue<Integer> waitQueue = new LinkedList<>();
    static Queue<Integer> serveQueue = new LinkedList<>();
    public static final int CUSTOMERS_PER_AGENT = 5;
    public static final int NUMBER_OF_AGENTS = 3;
    public static final int NUMBER_OF_CUSTOMERS = NUMBER_OF_AGENTS * CUSTOMERS_PER_AGENT;
    public static final int NUMBER_OF_THREADS = 10;


    public static void main(String[] args){
        ExecutorService es = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        es.submit(new Greeter());

        Thread[] agents = new Thread[NUMBER_OF_AGENTS];
        for(int i = 0; i < NUMBER_OF_AGENTS; i++){
            es.submit(new Agent(i));
        }
        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
            es.submit(new Customer(i));

            try {
                sleep(ThreadLocalRandom.current().nextInt(50, 200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
     es.shutdown();
    }
}
