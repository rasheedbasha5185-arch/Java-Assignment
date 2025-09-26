import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamLambdaDemo {

    public static void main(String[] args) {

        // Step 1: Create a list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("Original numbers: " + numbers);

        // Step 2: Filter even numbers and square them using Streams and Lambda
        List<Integer> squaredNumbers = numbers.stream() // Convert list to Stream
                .filter(n -> n % 2 == 0)               // Keep only even numbers
                .map(n -> n * n)                        // Square each number
                .collect(Collectors.toList());          // Collect results back to a List

        // Step 3: Print the squared numbers
        System.out.println("Squared even numbers: " + squaredNumbers);
    }
}
