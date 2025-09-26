import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CheckedUncheckedDemo {

    // ---------------------- Checked Exception Example ----------------------
    public static void readFile(String fileName) throws FileNotFoundException {
        // File may not exist → checked exception
        File file = new File(fileName);
        Scanner scanner = new Scanner(file); // throws FileNotFoundException
        System.out.println("File found and ready to read!");
        scanner.close();
    }

    // ---------------------- Unchecked Exception Example ----------------------
    public static int divide(int numerator, int denominator) {
        // Division by zero → unchecked exception (ArithmeticException)
        return numerator / denominator;
    }

    public static void main(String[] args) {

        System.out.println("-------- Checked Exception Demo --------");
        try {
            readFile("non_existing_file.txt"); // deliberately missing file
        } catch (FileNotFoundException e) {
            System.err.println("Checked Exception caught: " + e.getMessage());
        }

        System.out.println("\n-------- Unchecked Exception Demo --------");
        try {
            int result = divide(10, 0); // division by zero
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.err.println("Unchecked Exception caught: " + e.getMessage());
        }

        System.out.println("\nDemo completed!");
    }
}
