class NumberPrinterTask implements Runnable {
    private final int start;
    private final int end;

    public NumberPrinterTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
            try {
                Thread.sleep(100); // simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class TwoThreadsDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new NumberPrinterTask(1, 5), "Thread-1");
        Thread thread2 = new Thread(new NumberPrinterTask(6, 10), "Thread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both threads have finished execution.");
    }
}
