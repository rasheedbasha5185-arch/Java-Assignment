class AccessCounter {
    private int count = 0;

    // Synchronized to ensure thread-safe increment
    public synchronized void accessMethod() {
        count++;
        System.out.println(Thread.currentThread().getName() + " accessed method. Total count: " + count);
    }

    public int getCount() {
        return count;
    }
}

public class MethodAccessDemo {
    public static void main(String[] args) {
        AccessCounter counter = new AccessCounter();

        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                counter.accessMethod();
                try {
                    Thread.sleep(100); // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final method access count: " + counter.getCount());
    }
}
