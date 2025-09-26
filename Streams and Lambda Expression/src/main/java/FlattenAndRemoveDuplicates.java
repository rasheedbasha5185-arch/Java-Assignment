import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlattenAndRemoveDuplicates {
    public static void main(String[] args) {
        // Step 1: Create a list of lists of integers
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(2, 3, 5, 6),
                Arrays.asList(6, 7, 8, 1)
        );

        // Step 2: Flatten the structure and remove duplicates using Streams
        List<Integer> distinctNumbers = listOfLists.stream()
                .flatMap(List::stream)        // Flatten nested lists into a single stream
                .distinct()                   // Remove duplicate elements
                .collect(Collectors.toList()); // Collect back into a List

        // Step 3: Print distinct numbers
        distinctNumbers.forEach(System.out::println);
    }
}
