import java.util.Arrays;
import java.util.List;

public class StreamStringProcessing {
    public static void main(String[] args) {
        // Step 1: Create a list of strings
        List<String> words = Arrays.asList("hello", "world", "java", "streams", "lambda");

        // Step 2: Use Stream to process strings
        words.stream()
                .peek(word -> System.out.println("Original word: " + word)) // Debug: print each word before processing
                .map(word -> {
                    System.out.println("Length of \"" + word + "\" = " + word.length()); // Print length
                    return word.toUpperCase(); // Convert to uppercase
                })
                .peek(word -> System.out.println("After converting to uppercase: " + word)) // Debug after uppercase
                .forEach(System.out::println); // Final print of processed string
    }
}
