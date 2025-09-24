import java.util.Arrays;

/**
 * Custom implementation of ArrayList with generics.
 * Supports dynamic resizing, adding/removing elements, and basic utility methods.
 * Now includes addAll() methods as well.
 */
public class CustomArrayList<T> {

    private T[] arr;      // Internal storage
    private int size = 0; // Number of elements actually in the list
    private static final int DEFAULT_CAPACITY = 10;

    // Constructor with default capacity
    public CustomArrayList() {
        this.arr = (T[]) new Object[DEFAULT_CAPACITY];
    }

    // Constructor with custom capacity
    public CustomArrayList(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be greater than 0");
        this.arr = (T[]) new Object[capacity];
    }

    /**
     * Adds an element at the end of the list
     */
    public void add(T val) {
        ensureCapacity(size + 1);
        arr[size++] = val;
    }

    /**
     * Inserts an element at a specific index
     */
    public void add(int index, T val) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        ensureCapacity(size + 1);
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = val;
        size++;
    }

    /**
     * Adds all elements from the given array at the end
     */
    public void addAll(T[] values) {
        ensureCapacity(size + values.length);
        for (T val : values) {
            arr[size++] = val;
        }
    }

    /**
     * Adds all elements from the given array starting at a specific index
     */
    public void addAll(int index, T[] values) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        ensureCapacity(size + values.length);
        System.arraycopy(arr, index, arr, index + values.length, size - index);
        for (int i = 0; i < values.length; i++) {
            arr[index + i] = values[i];
        }
        size += values.length;
    }

    /**
     * Returns the element at the specified index
     */
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return arr[index];
    }

    /**
     * Removes the element at the specified index and returns it
     */
    public T remove(int index) {
        T val = get(index);
        System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        arr[--size] = null; // Avoid memory leak
        return val;
    }

    /**
     * Returns the current number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the list contains a specific element
     */
    public boolean contains(T val) {
        return indexOf(val) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the element, or -1 if not found
     */
    public int indexOf(T val) {
        for (int i = 0; i < size; i++) {
            if ((arr[i] == null && val == null) || (arr[i] != null && arr[i].equals(val))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Clears the list
     */
    public void clear() {
        for (int i = 0; i < size; i++) arr[i] = null;
        size = 0;
    }

    /**
     * Ensures internal array has enough capacity, doubles size if needed
     */
    private void ensureCapacity(int minCap) {
        if (minCap > arr.length) {
            int newCap = Math.max(arr.length * 2, minCap);
            arr = Arrays.copyOf(arr, newCap);
        }
    }

    /**
     * Returns string representation of the list
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(arr, size));
    }

    // ------------------- Demo / Testing -------------------
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>(2);

        // Test add()
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("After adds: " + list);

        // Test add(index, value)
        list.add(1, 99);
        System.out.println("After add(1,99): " + list);

        // Test remove()
        list.remove(2);
        System.out.println("After remove(2): " + list);

        // Test contains() and indexOf()
        System.out.println("Contains 3? " + list.contains(3));
        System.out.println("Index of 99: " + list.indexOf(99));

        // Test addAll(T[] values)
        Integer[] arrToAdd = {7, 8, 9};
        list.addAll(arrToAdd);
        System.out.println("After addAll at end: " + list);

        // Test addAll(int index, T[] values)
        Integer[] moreValues = {100, 200};
        list.addAll(2, moreValues);
        System.out.println("After addAll at index 2: " + list);

        // Test clear()
        list.clear();
        System.out.println("After clear: " + list + ", size=" + list.size());
    }
}
