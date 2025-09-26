import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterDemo {

    // Shared Atomic Counter
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {

        // Create multiple threads that increment the counter
        Thread t1 = new Thread(() -> incrementCounter(1000), "Thread-1");
        Thread t2 = new Thread(() -> incrementCounter(1000), "Thread-2");
        Thread t3 = new Thread(() -> incrementCounter(1000), "Thread-3");

        // Start all threads
        t1.start();
        t2.start();
        t3.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final counter value
        System.out.println("Final Counter Value: " + counter.get());
    }

    // Method to increment counter atomically
    private static void incrementCounter(int times) {
        for (int i = 0; i < times; i++) {
            counter.incrementAndGet(); // Atomic increment
        }
        System.out.println(Thread.currentThread().getName() + " finished incrementing.");
    }
}
