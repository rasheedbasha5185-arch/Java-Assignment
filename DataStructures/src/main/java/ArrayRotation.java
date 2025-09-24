import java.util.Arrays;
import java.util.Scanner;

public class ArrayRotation {

    // Rotate array to the right by k steps
    public static void rotateRight(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return;

        k = k % n; // Handle cases where k > n

        reverse(arr, 0, n - 1);      // Step 1: Reverse the whole array
        reverse(arr, 0, k - 1);      // Step 2: Reverse first k elements
        reverse(arr, k, n - 1);      // Step 3: Reverse remaining elements
    }

    // Helper method to reverse array from start to end
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of elements:");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Enter number of steps to rotate:");
        int k = sc.nextInt();

        System.out.println("Original array: " + Arrays.toString(arr));

        rotateRight(arr, k);

        System.out.println("Array after rotating " + k + " steps to the right: " + Arrays.toString(arr));

        sc.close();
    }
}
