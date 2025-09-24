import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchProgram {

    // ==================== Binary Search Method ====================
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Prevents integer overflow
            if (arr[mid] == target) {
                return mid; // Found target, return index
            } else if (arr[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        return -1; // Not found
    }

    // ==================== Main Method ====================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of elements:");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements (integers only):");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr); // Binary search requires sorted array
        System.out.println("Sorted array: " + Arrays.toString(arr));

        System.out.println("Enter the value to search:");
        int target = sc.nextInt();

        int index = binarySearch(arr, target);

        if (index != -1) {
            System.out.println("Value found at index: " + index);
        } else {
            System.out.println("Value not found in the array.");
        }

        sc.close();
    }
}
