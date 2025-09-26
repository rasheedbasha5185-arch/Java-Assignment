import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    // Semaphore allows maximum 3 threads to access resource concurrently
    private static final Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        // Create 6 threads simulating multiple users trying to access a resource
        for (int i = 1; i <= 6; i++) {
            Thread t = new Thread(new Worker(), "Worker-" + i);
            t.start();
        }
    }

    // Worker class simulates a thread accessing a shared resource
    static class Worker implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting to acquire semaphore...");

                // Acquire semaphore before accessing resource
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " acquired semaphore, working...");

                // Simulate some work
                Thread.sleep(1000);

                System.out.println(Thread.currentThread().getName() + " releasing semaphore.");

                // Release semaphore after work is done
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
