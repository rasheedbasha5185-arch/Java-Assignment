// Q7: Program to demonstrate ThreadLocal

class Worker implements Runnable {
    // ThreadLocal variable ensures each thread has its own copy
    private static final ThreadLocal<Integer> threadLocalValue = ThreadLocal.withInitial(() -> 0);

    private final String name;

    public Worker(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // Each thread modifies its own value
        for (int i = 1; i <= 3; i++) {
            int newValue = threadLocalValue.get() + 1;
            threadLocalValue.set(newValue);
            System.out.println(name + " - Iteration " + i + " - Value: " + threadLocalValue.get());

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ThreadLocalDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker("Thread-1"));
        Thread t2 = new Thread(new Worker("Thread-2"));
        Thread t3 = new Thread(new Worker("Thread-3"));

        t1.start();
        t2.start();
        t3.start();
    }
}
