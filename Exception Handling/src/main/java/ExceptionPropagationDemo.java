// Step 1: Custom Exception
class MyCustomException extends Exception {
    public MyCustomException(String message) {
        super(message); // Pass the message to the Exception class
    }
}

public class ExceptionPropagationDemo {

    // ---------------------- methodC ----------------------
    public static void methodC() throws MyCustomException {
        System.out.println("Inside methodC: About to throw MyCustomException");
        // Throw custom exception deliberately
        throw new MyCustomException("Exception thrown from methodC");
    }

    // ---------------------- methodB ----------------------
    public static void methodB() throws MyCustomException {
        System.out.println("Inside methodB: Calling methodC");
        methodC(); // propagate exception to caller (methodB)
    }

    // ---------------------- methodA ----------------------
    public static void methodA() {
        System.out.println("Inside methodA: Calling methodB");
        try {
            methodB(); // methodA calls methodB
        } catch (MyCustomException e) {
            // Catch exception propagated from methodC
            System.err.println("Caught exception in methodA: " + e.getMessage());
        }
    }

    // ---------------------- main ----------------------
    public static void main(String[] args) {
        System.out.println("Demonstrating Exception Propagation");
        methodA(); // Start chain
        System.out.println("Program continues after handling exception");
    }
}