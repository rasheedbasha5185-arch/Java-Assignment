import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class NumberPrinter {
    private final int max;
    private int number = 1; // shared counter
    private final Object lock = new Object();

    // queue to pass odd-even pairs to Sum thread
    private final BlockingQueue<int[]> sumQueue = new LinkedBlockingQueue<>();

    // flag to ensure sum is printed before next number
    private boolean sumPrinted = true;

    public NumberPrinter(int max) {
        this.max = max;
    }

    public void printOdd() {
        synchronized (lock) {
            while (number <= max) {
                while (number % 2 == 0 || !sumPrinted) { // wait if even or sum not printed
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                int oddNumber = number;
                System.out.println(Thread.currentThread().getName() + " - Odd: " + oddNumber);
                number++;
                sumPrinted = false; // sum needs to be printed after even
                lock.notifyAll();
            }
        }
    }

    public void printEven() {
        synchronized (lock) {
            while (number <= max) {
                while (number % 2 != 0) { // wait if odd
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                int evenNumber = number;
                int oddNumber = evenNumber - 1;
                System.out.println(Thread.currentThread().getName() + " - Even: " + evenNumber);

                // put the pair in queue
                sumQueue.add(new int[]{oddNumber, evenNumber});
                number++;
                lock.notifyAll();
            }
        }
    }

    public void printSum() {
        try {
            while (true) {
                int[] pair = sumQueue.take(); // waits until a pair is available
                int sum = pair[0] + pair[1];
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " - Sum: " + sum);
                    sumPrinted = true; // sum printed, odd can continue
                    lock.notifyAll();

                    if (pair[1] >= max) break; // stop condition
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class EvenOddThreadSync {
    public static void main(String[] args) {
        int maxNumber = 6; // change as needed
        NumberPrinter printer = new NumberPrinter(maxNumber);

        Thread oddThread = new Thread(printer::printOdd, "OddThread");
        Thread evenThread = new Thread(printer::printEven, "EvenThread");
        Thread sumThread = new Thread(printer::printSum, "SumThread");

        oddThread.start();
        evenThread.start();
        sumThread.start();
    }
}
