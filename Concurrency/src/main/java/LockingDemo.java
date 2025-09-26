// Shared Resource Class
class Printer {
    private int jobCount = 0;

    // ===== Class-Level Locking =====
    public static synchronized void printClassLevel(String message) {
        System.out.println(Thread.currentThread().getName() + " printing (Class-Level): " + message);
        try {
            Thread.sleep(100); // simulate printing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ===== Object-Level Locking =====
    public void printObjectLevel(String message) {
        synchronized (this) {
            jobCount++;
            System.out.println(Thread.currentThread().getName() + " printing (Object-Level) #" + jobCount + ": " + message);
            try {
                Thread.sleep(100); // simulate printing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Main Class
public class LockingDemo {
    public static void main(String[] args) {
        Printer printer1 = new Printer();
        Printer printer2 = new Printer();

        // Threads for Class-Level Locking (static method)
        Thread t1 = new Thread(() -> Printer.printClassLevel("Document A"), "Thread-Class-1");
        Thread t2 = new Thread(() -> Printer.printClassLevel("Document B"), "Thread-Class-2");

        // Threads for Object-Level Locking (different instances)
        Thread t3 = new Thread(() -> printer1.printObjectLevel("Report 1"), "Thread-Object-1");
        Thread t4 = new Thread(() -> printer2.printObjectLevel("Report 2"), "Thread-Object-2");

        // Start Class-Level Threads
        t1.start();
        t2.start();

        try { t1.join(); t2.join(); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("Class-Level printing completed.\n");

        // Start Object-Level Threads
        t3.start();
        t4.start();

        try { t3.join(); t4.join(); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("Object-Level printing completed.");
    }
}
