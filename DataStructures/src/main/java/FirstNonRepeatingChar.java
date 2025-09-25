import java.util.*;

class FirstNonRepeatingChar {
    public static char findFirstNonRepeating(String str) {
        Map<Character, Integer> freqMap = new LinkedHashMap<>();

        // Count frequency of each character
        for (char c : str.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Find the first character with frequency 1
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return '\0'; // Return null character if none found
    }

    public static void main(String[] args) {
        String input = "abcdachyt";
        char result = findFirstNonRepeating(input);

        if (result != '\0') {
            System.out.println("First non-repeating character: " + result);
        } else {
            System.out.println("No non-repeating character found");
        }
    }
}
