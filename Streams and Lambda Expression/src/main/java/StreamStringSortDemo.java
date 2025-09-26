import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamStringSortDemo {

    public static void main(String[] args) {

        // Step 1: Create a list of strings
        List<String> words = Arrays.asList("apple", "banana", "kiwi", "grapefruit", "mango", "pineapple");

        System.out.println("Original list: " + words);

        // Step 2: Sort strings by length in descending order and limit to first 3
        List<String> top3Longest = words.stream()                     // Convert list to Stream
                .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length())) // Comparator for descending length
                .limit(3)                                             // Keep only first 3 elements
                .collect(Collectors.toList());                        // Collect results into a List

        // Step 3: Print the result
        System.out.println("Top 3 longest words: " + top3Longest);
    }
}
