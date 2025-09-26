// Shared Resource Class
class Counter {
    private int count = 0;

    // ===== Method-Level Synchronization =====
    public synchronized void incrementMethodLevel() {
        count++;
        System.out.println(Thread.currentThread().getName() + " incrementMethodLevel: " + count);
    }

    // ===== Object-Level Synchronization =====
    public void incrementObjectLevel() {
        synchronized (this) {
            count++;
            System.out.println(Thread.currentThread().getName() + " incrementObjectLevel: " + count);
        }
    }

    public int getCount() {
        return count;
    }
}

// Main Class
public class SynchronizationDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();

        // Threads for Method-Level Synchronization
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) counter.incrementMethodLevel();
        }, "Thread-Method-1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) counter.incrementMethodLevel();
        }, "Thread-Method-2");

        // Threads for Object-Level Synchronization
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) counter.incrementObjectLevel();
        }, "Thread-Object-1");

        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 5; i++) counter.incrementObjectLevel();
        }, "Thread-Object-2");

        // Start Method-Level Threads
        t1.start();
        t2.start();

        // Wait for method-level threads to finish
        try { t1.join(); t2.join(); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("Count after Method-Level Synchronization: " + counter.getCount());

        // Start Object-Level Threads
        t3.start();
        t4.start();

        // Wait for object-level threads to finish
        try { t3.join(); t4.join(); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("Count after Object-Level Synchronization: " + counter.getCount());
    }
}
