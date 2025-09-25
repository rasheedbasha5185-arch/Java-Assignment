import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class SharedBuffer {
    private final Queue<Integer> buffer = new LinkedList<>(); // shared storage
    private final int capacity; // maximum size of buffer

    // Lock & Conditions
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();   // condition when buffer is full
    private final Condition notEmpty = lock.newCondition();  // condition when buffer is empty

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    // Producer puts items
    public void produce(int item) throws InterruptedException {
        lock.lock();  // acquire lock
        try {
            while (buffer.size() == capacity) { // wait if buffer is full
                System.out.println(Thread.currentThread().getName() + " waits, Buffer FULL");
                notFull.await();
            }
            buffer.add(item);
            System.out.println(Thread.currentThread().getName() + " produced " + item);
            notEmpty.signalAll(); // wake up consumers
        } finally {
            lock.unlock(); // release lock
        }
    }

    // Consumer takes items
    public int consume() throws InterruptedException {
        lock.lock();  // acquire lock
        try {
            while (buffer.isEmpty()) { // wait if buffer is empty
                System.out.println(Thread.currentThread().getName() + " waits, Buffer EMPTY");
                notEmpty.await();
            }
            int item = buffer.poll();
            System.out.println(Thread.currentThread().getName() + " consumed " + item);
            notFull.signalAll(); // wake up producers
            return item;
        } finally {
            lock.unlock(); // release lock
        }
    }
}

// Producer Thread
class Producer implements Runnable {
    private final SharedBuffer buffer;
    private final int itemsToProduce;

    public Producer(SharedBuffer buffer, int itemsToProduce) {
        this.buffer = buffer;
        this.itemsToProduce = itemsToProduce;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= itemsToProduce; i++) {
                buffer.produce(i);
                Thread.sleep(200); // simulate time to produce
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Consumer Thread
class Consumer implements Runnable {
    private final SharedBuffer buffer;
    private final int itemsToConsume;

    public Consumer(SharedBuffer buffer, int itemsToConsume) {
        this.buffer = buffer;
        this.itemsToConsume = itemsToConsume;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= itemsToConsume; i++) {
                buffer.consume();
                Thread.sleep(300); // simulate time to consume
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Demo
public class ProducerConsumerWithReentrantLock {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer(5); // capacity of buffer

        // Multiple producers and consumers
        Thread producer1 = new Thread(new Producer(buffer, 5), "Producer-1");
        Thread producer2 = new Thread(new Producer(buffer, 5), "Producer-2");
        Thread consumer1 = new Thread(new Consumer(buffer, 5), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(buffer, 5), "Consumer-2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
