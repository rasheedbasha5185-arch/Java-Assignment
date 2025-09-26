import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerDemo {

    // Shared queue with capacity 5
    private static final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {

        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    System.out.println("Producer trying to put: " + i);
                    queue.put(i); // Blocks if queue is full
                    System.out.println("Producer added: " + i);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer");

        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    System.out.println("Consumer trying to take...");
                    int value = queue.take(); // Blocks if queue is empty
                    System.out.println("Consumer consumed: " + value);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer");

        // Start threads
        producer.start();
        consumer.start();
    }
}
