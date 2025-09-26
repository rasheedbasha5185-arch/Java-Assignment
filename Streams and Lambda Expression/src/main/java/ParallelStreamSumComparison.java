import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreamSumComparison {
    public static void main(String[] args) {
        int size = 10_000_000; // Size of the list (10 million integers)

        // Step 1: Generate a large list of random integers
        List<Integer> numbers = new Random()
                .ints(size, 1, 100)      // Generate 'size' integers in range [1, 100)
                .boxed()                 // Convert primitive int to Integer
                .collect(Collectors.toList()); // Collect into List<Integer>

        // Step 2: Sequential stream sum
        long startSequential = System.currentTimeMillis();
        long sumSequential = numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long endSequential = System.currentTimeMillis();
        System.out.println("Sequential sum: " + sumSequential +
                " | Time: " + (endSequential - startSequential) + " ms");

        // Step 3: Parallel stream sum
        long startParallel = System.currentTimeMillis();
        long sumParallel = numbers.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        long endParallel = System.currentTimeMillis();
        System.out.println("Parallel sum: " + sumParallel +
                " | Time: " + (endParallel - startParallel) + " ms");
    }
}
