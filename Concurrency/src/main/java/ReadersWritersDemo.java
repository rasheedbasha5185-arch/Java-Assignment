import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ReadersWritersDemo {

    private static int sharedData = 0; // Shared resource
    private static int readers = 0;    // Number of active readers
    private static final Lock lock = new ReentrantLock();
    private static final Condition canWrite = lock.newCondition();

    public static void main(String[] args) {
        // Writer thread
        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                write(i);
                sleep(500);
            }
        }, "Writer");

        // Reader threads
        for (int i = 1; i <= 3; i++) {
            Thread reader = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    read();
                    sleep(300);
                }
            }, "Reader-" + i);
            reader.start();
        }

        writer.start();
    }

    // Reader method
    private static void read() {
        lock.lock();
        try {
            readers++; // Indicate this reader is active
        } finally {
            lock.unlock();
        }

        System.out.println(Thread.currentThread().getName() + " reading sharedData: " + sharedData);

        lock.lock();
        try {
            readers--; // Done reading
            if (readers == 0) {
                canWrite.signal(); // Notify writer if no readers left
            }
        } finally {
            lock.unlock();
        }
    }

    // Writer method
    private static void write(int value) {
        lock.lock();
        try {
            while (readers > 0) { // Wait if any reader is active
                canWrite.await();
            }
            sharedData = value; // Update shared resource
            System.out.println(Thread.currentThread().getName() + " wrote: " + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
