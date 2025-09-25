import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// Step 1: Domain class (Order)
class Order {
    private final String id;
    private final String customer;

    public Order(String id, String customer) {
        this.id = id;
        this.customer = customer;
    }

    public String getId() { return id; }
    public String getCustomer() { return customer; }
}

// Step 2: Producer
class Producer1 implements Runnable {
    private final BlockingQueue<Order> queue;

    public Producer1(BlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                Order order = new Order("Order-" + i, "Customer-" + i);
                queue.put(order); // automatically waits if queue is full
                System.out.println(Thread.currentThread().getName() + " placed " + order.getId());
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Step 3: Consumer
class Consumer1 implements Runnable {
    private final BlockingQueue<Order> queue;

    public Consumer1(BlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = queue.take(); // automatically waits if queue is empty
                System.out.println(Thread.currentThread().getName() + " processing " + order.getId() +
                        " for " + order.getCustomer());
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Step 4: Demo
public class ProducerConsumerLBQDemo {
    public static void main(String[] args) {
        BlockingQueue<Order> queue = new LinkedBlockingQueue<>(3); // capacity = 3

        Thread producer = new Thread(new Producer1(queue), "Producer");
        Thread consumer1 = new Thread(new Consumer1(queue), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer1(queue), "Consumer-2");

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
