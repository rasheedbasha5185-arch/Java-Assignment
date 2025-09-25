import java.util.LinkedList;
import java.util.Queue;

class MyThreadPool {
    private final int numThreads;
    private final PoolWorker[] workers;
    private final Queue<Runnable> taskQueue;

    public MyThreadPool(int numThreads) {
        this.numThreads = numThreads;
        taskQueue = new LinkedList<>();
        workers = new PoolWorker[numThreads];

        for (int i = 0; i < numThreads; i++) {
            workers[i] = new PoolWorker();
            workers[i].start();
        }
    }

    public void submit(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify(); // Notify waiting threads that a task is available
        }
    }

    private class PoolWorker extends Thread {
        public void run() {
            Runnable task;

            while (true) {
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait(); // Wait for a task to be available
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = taskQueue.poll(); // Take the next task
                }
                try {
                    task.run(); // Execute the task
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

// Demo class to test MyThreadPool
public class ThreadPoolDemo {
    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(3); // 3 threads in pool

        // Submit 6 tasks
        for (int i = 1; i <= 6; i++) {
            int taskId = i;
            pool.submit(() -> {
                System.out.println("Executing task " + taskId + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Completed task " + taskId + " by " + Thread.currentThread().getName());
            });
        }
    }
}
