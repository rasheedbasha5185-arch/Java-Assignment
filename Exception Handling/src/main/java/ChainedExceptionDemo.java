// Step 1: Custom Exception Class
class CustomException extends Exception {
    public CustomException(String message, Throwable cause) {
        super(message, cause); // pass both message and original exception (cause)
    }
}

// Step 2: Class that performs arithmetic operation
class ArithmeticOperations {
    public static int divide(int numerator, int denominator) {
        return numerator / denominator; // may throw ArithmeticException
    }
}

// Step 3: Main Program
public class ChainedExceptionDemo {

    public static void main(String[] args) {
        try {
            System.out.println("Performing division operation...");
            int result = ArithmeticOperations.divide(10, 0); // deliberate division by zero
            System.out.println("Result: " + result);
        } catch (ArithmeticException ae) {
            // Catch the original exception and throw a custom exception
            try {
                throw new CustomException("CustomException: Error during division operation", ae);
            } catch (CustomException ce) {
                System.err.println(ce.getMessage());
                System.err.println("Original cause: " + ce.getCause());
            }
        }

        System.out.println("Program continues execution after handling chained exception.");
    }
}