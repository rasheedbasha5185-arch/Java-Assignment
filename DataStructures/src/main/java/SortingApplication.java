import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingApplication {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java SortingApplication <SORT_TYPE> <values...>");
            return;
        }

        String sortType = args[0].toUpperCase(); // BUBBLE, QUICK, MERGE
        List<Integer> numbers = new ArrayList<>();

        // Parse integer values from command line, ignore non-integers
        for (int i = 1; i < args.length; i++) {
            try {
                numbers.add(Integer.parseInt(args[i]));
            } catch (NumberFormatException e) {
                // Ignore strings or invalid numbers
            }
        }

        int[] arr = numbers.stream().mapToInt(Integer::intValue).toArray();

        long startTime = System.nanoTime();

        switch (sortType) {
            case "BUBBLE":
                bubbleSort(arr);
                break;
            case "QUICK":
                quickSort(arr, 0, arr.length - 1);
                break;
            case "MERGE":
                arr = mergeSort(arr);
                break;
            default:
                System.out.println("Invalid sort type. Use BUBBLE, QUICK, or MERGE.");
                return;
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println("Sorted Values: " + Arrays.toString(arr));
        System.out.println("Time taken (nanoseconds): " + duration);

        // Recommendation
        System.out.println("Recommendation: For small datasets, BUBBLE is fine; for larger datasets, QUICK or MERGE are better due to faster performance.");
    }

    // ================== Bubble Sort ==================
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // array already sorted
        }
    }

    // ================== Quick Sort ==================
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // ================== Merge Sort ==================
    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) return arr;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];

        return result;
    }
}
