import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class ParallelSortDemo {

    // ===== Merge Sort using Fork-Join =====
    static class ParallelMergeSort extends RecursiveTask<int[]> {
        private final int[] array;

        public ParallelMergeSort(int[] array) {
            this.array = array;
        }

        @Override
        protected int[] compute() {
            if (array.length <= 1) return array; // Base case

            int mid = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);

            ParallelMergeSort leftTask = new ParallelMergeSort(left);
            ParallelMergeSort rightTask = new ParallelMergeSort(right);

            leftTask.fork();           // Fork left subtask
            int[] rightResult = rightTask.compute(); // Compute right in current thread
            int[] leftResult = leftTask.join();      // Wait for left

            return merge(leftResult, rightResult);
        }

        private int[] merge(int[] left, int[] right) {
            int[] result = new int[left.length + right.length];
            int i = 0, j = 0, k = 0;

            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) result[k++] = left[i++];
                else result[k++] = right[j++];
            }
            while (i < left.length) result[k++] = left[i++];
            while (j < right.length) result[k++] = right[j++];
            return result;
        }
    }

    public static void main(String[] args) {
        int[] array = {9, 4, 1, 7, 3, 6, 2, 8, 5};

        System.out.println("Original Array: " + Arrays.toString(array));

        ForkJoinPool pool = new ForkJoinPool();

        // ===== Merge Sort Execution =====
       /* ParallelMergeSort mergeSortTask = new ParallelMergeSort(array);
        int[] sortedArrayMerge = pool.invoke(mergeSortTask);
        System.out.println("Sorted Array (Merge Sort): " + Arrays.toString(sortedArrayMerge));
        */

        // ===== Quick Sort Logic (commented, for demonstration) =====

        int[] quickSortArray = array.clone();
        quickSort(quickSortArray, 0, quickSortArray.length - 1);
        System.out.println("Sorted Array (Quick Sort): " + Arrays.toString(quickSortArray));

    }

    // ===== Quick Sort Method (for reference) =====

    private static void quickSort(int[] arr, int low, int high) {
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

}
