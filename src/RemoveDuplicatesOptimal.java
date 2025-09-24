import java.util.ArrayList;
import java.util.Collections;

public class RemoveDuplicatesOptimal {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(10);
        list.add(30);
        list.add(20);
        list.add(40);

        System.out.println("Original List: " + list);

        removeDuplicates(list);

        System.out.println("List after removing duplicates: " + list);
    }

    public static void removeDuplicates(ArrayList<Integer> list) {
        // Step 1: Sort the list (in-place)
        Collections.sort(list);

        // Step 2: Remove consecutive duplicates
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                list.remove(i + 1);
                i--; // Adjust index after removal
            }
        }
    }
}
