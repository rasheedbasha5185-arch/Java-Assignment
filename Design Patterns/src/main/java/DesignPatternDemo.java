// ===== Singleton Pattern =====
class Singleton {
    private static Singleton instance;

    private Singleton() {
        System.out.println("Singleton instance created");
    }

    public static Singleton getInstance() {
        if (instance == null) { // lazy initialization
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton");
    }
}

// ===== Proxy Pattern =====
interface Service {
    void execute();
}

class RealService implements Service {
    public void execute() {
        System.out.println("Executing Real Service");
    }
}

class ProxyService implements Service {
    private RealService realService;

    public void execute() {
        if (realService == null) {
            realService = new RealService(); // lazy initialization
        }
        System.out.println("Proxy: Before execution");
        realService.execute();
        System.out.println("Proxy: After execution");
    }
}

// ===== Front Controller Pattern =====
class FrontController {
    private Dispatcher dispatcher;

    public FrontController() {
        dispatcher = new Dispatcher();
    }

    public void handleRequest(String request) {
        System.out.println("FrontController: Handling request: " + request);
        // Preprocessing like logging, auth etc.
        dispatcher.dispatch(request);
    }
}

class Dispatcher {
    public void dispatch(String request) {
        if ("HOME".equalsIgnoreCase(request)) {
            System.out.println("Displaying Home Page");
        } else if ("LOGIN".equalsIgnoreCase(request)) {
            System.out.println("Displaying Login Page");
        } else {
            System.out.println("Unknown Request");
        }
    }
}

// ===== Main Class =====
public class DesignPatternDemo {
    public static void main(String[] args) {
        // Singleton
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println("Are singleton instances equal? " + (singleton1 == singleton2));
        singleton1.showMessage();

        System.out.println("------");

        // Proxy
        Service service = new ProxyService();
        service.execute();

        System.out.println("------");

        // Front Controller
        FrontController frontController = new FrontController();
        frontController.handleRequest("HOME");
        frontController.handleRequest("LOGIN");
        frontController.handleRequest("PROFILE");
    }
}
