import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockDemo {

    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        // Thread 1 tries to lock LOCK1 then LOCK2
        Thread t1 = new Thread(() -> {
            synchronized (LOCK1) {
                System.out.println(Thread.currentThread().getName() + " acquired LOCK1");
                sleep(100);
                synchronized (LOCK2) {
                    System.out.println(Thread.currentThread().getName() + " acquired LOCK2");
                }
            }
        }, "Thread-1");

        // Thread 2 tries to lock LOCK2 then LOCK1
        Thread t2 = new Thread(() -> {
            synchronized (LOCK2) {
                System.out.println(Thread.currentThread().getName() + " acquired LOCK2");
                sleep(100);
                synchronized (LOCK1) {
                    System.out.println(Thread.currentThread().getName() + " acquired LOCK1");
                }
            }
        }, "Thread-2");

        t1.start();
        t2.start();

        // Give threads time to possibly deadlock
        Thread.sleep(500);

        // Detect deadlocks
        detectAndResolveDeadlock();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to detect deadlocks using ThreadMXBean
    private static void detectAndResolveDeadlock() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = bean.findDeadlockedThreads(); // Detect deadlocked threads

        if (deadlockedThreads != null) {
            System.out.println("Deadlock detected! Resolving...");
            ThreadInfo[] infos = bean.getThreadInfo(deadlockedThreads);

            for (ThreadInfo info : infos) {
                System.out.println("Thread in deadlock: " + info.getThreadName());
            }

            // Interrupt deadlocked threads to resolve
            for (long threadId : deadlockedThreads) {
                for (Thread t : Thread.getAllStackTraces().keySet()) {
                    if (t.getId() == threadId) {
                        System.out.println("Interrupting " + t.getName());
                        t.interrupt();
                    }
                }
            }
        } else {
            System.out.println("No deadlock detected.");
        }
    }
}
