import java.util.Scanner;

public class NestedTryCatchDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nested Try-Catch Demo Started");

        try { // Outer try block
            System.out.print("Enter numerator: ");
            String numeratorInput = scanner.nextLine();

            System.out.print("Enter denominator: ");
            String denominatorInput = scanner.nextLine();

            try { // Inner try block
                // Convert inputs to integers
                int numerator = Integer.parseInt(numeratorInput);
                int denominator = Integer.parseInt(denominatorInput);

                // Perform division
                int result = numerator / denominator;
                System.out.println("Result of division: " + result);

            } catch (ArithmeticException ae) {
                // Catch division by zero
                System.err.println("ArithmeticException caught: Division by zero is not allowed.");
            }

        } catch (NumberFormatException nfe) {
            // Catch non-numeric input in outer try
            System.err.println("NumberFormatException caught: Please enter valid numeric input.");
        } finally {
            scanner.close(); // Always close resources
            System.out.println("Scanner closed, program finished.");
        }
    }
}
