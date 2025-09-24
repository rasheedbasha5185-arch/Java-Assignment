import java.util.*;

public class SortStringsAndChars {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("apple", "banana", "grape", "kiwi"));
        System.out.println("Original List: " + list);

        // Step 1: Sort characters inside each string in descending order
        List<String> charSortedList = new ArrayList<>();
        for (String str : list) {
            charSortedList.add(sortCharactersDescending(str));
        }

        // Step 2: Sort the list of strings in descending order using Comparator
        charSortedList.sort(Comparator.reverseOrder());

        System.out.println("Sorted List (strings & characters descending): " + charSortedList);
    }

    // Method to sort characters of a string in descending order
    private static String sortCharactersDescending(String str) {
        char[] chars = str.toCharArray();
        Character[] charObjects = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) charObjects[i] = chars[i];

        Arrays.sort(charObjects, Comparator.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for (Character ch : charObjects) sb.append(ch);
        return sb.toString();
    }
}
