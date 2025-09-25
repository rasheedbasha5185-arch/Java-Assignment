class TaskThread extends Thread {
    private final String taskName;

    public TaskThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName + " started.");
        try {
            Thread.sleep(1000); // Simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(taskName + " completed.");
    }
}

public class ThreadJoinDemo {
    public static void main(String[] args) {
        TaskThread t1 = new TaskThread("Task 1");
        TaskThread t2 = new TaskThread("Task 2");
        TaskThread t3 = new TaskThread("Task 3");

        try {
            t1.start();
            t1.join(); // Wait for t1 to complete before starting t2

            t2.start();
            t2.join(); // Wait for t2 to complete before starting t3

            t3.start();
            t3.join(); // Wait for t3 to complete before main thread continues
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed. Main thread resumes.");
    }
}
