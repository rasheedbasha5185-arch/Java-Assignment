import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupWordsByFirstLetter {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\shaikrasheed.basha\\OneDrive - VCTI\\Desktop\\words.txt";  // Make sure this file exists in your project directory

        try {
            // Step 1: Read all lines from the file into a List of Strings
            List<String> words = Files.readAllLines(Paths.get(filePath));

            // Step 2: Use Streams + Lambda to group words by first letter
            Map<Character, Long> groupedResult = words.stream()
                    .filter(word -> !word.isEmpty()) // Avoid empty lines
                    .collect(Collectors.groupingBy(
                            word -> word.charAt(0),    // Grouping criteria (first character)
                            Collectors.counting()       // Count words in each group
                    ));

            // Step 3: Print the result
            groupedResult.forEach((letter, count) ->
                    System.out.println(letter + " : " + count));

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
