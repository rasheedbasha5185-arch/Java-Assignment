import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordFrequencyConcurrent {

    public static void main(String[] args) {
        // Step 1: Read multiple paragraphs from console input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter multiple paragraphs (type 'END' to finish):");

        StringBuilder textBuilder = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("END")) {
                break;
            }
            textBuilder.append(line).append(" ");
        }
        scanner.close();

        String text = textBuilder.toString().toLowerCase();

        // Step 2: Split text into words using non-word characters
        String[] words = text.split("\\W+");

        // Step 3: Create ConcurrentHashMap for word frequencies
        ConcurrentHashMap<String, Integer> wordCountMap = new ConcurrentHashMap<>();

        // Step 4: Create a thread pool (adjust size based on CPU cores)
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(cores);

        // Step 5: Submit each word for concurrent processing
        for (String word : words) {
            if (!word.isEmpty()) { // Skip empty strings
                executor.submit(() -> {
                    // Atomic operation: merge handles concurrent updates
                    wordCountMap.merge(word, 1, Integer::sum);
                });
            }
        }

        // Step 6: Shutdown executor and wait for tasks to complete
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Waiting for all threads to finish
        }

        // Step 7: Print the word frequency map
        System.out.println("\nWord Frequencies:");
        wordCountMap.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
