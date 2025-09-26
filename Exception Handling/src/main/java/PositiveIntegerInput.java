import java.util.Scanner;

// Step 1: Create Custom Exception
class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message); // Pass the error message to Exception class
    }
}

// Step 2: Main Program
public class PositiveIntegerInput {

    // Method to get positive integer input
    public static int getPositiveInteger(int input) throws InvalidInputException {
        if (input <= 0) {
            // Throw custom exception if input is invalid
            throw new InvalidInputException("Input must be a positive integer. You entered: " + input);
        }
        return input; // return valid input
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter a positive integer: ");
            int userInput = scanner.nextInt(); // Read user input

            // Validate input using custom exception
            int validInput = getPositiveInteger(userInput);
            System.out.println("You entered a valid positive integer: " + validInput);

        } catch (InvalidInputException e) {
            // Catch and display custom exception
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            // Catch other exceptions like InputMismatchException
            System.err.println("Invalid input type! Please enter an integer.");
        } finally {
            scanner.close();
        }
    }
}