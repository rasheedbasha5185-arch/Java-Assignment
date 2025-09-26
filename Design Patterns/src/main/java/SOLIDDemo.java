// ===== SRP: Single Responsibility Principle =====
class Invoice {
    private double amount;

    public Invoice(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

// SRP: Separate class for printing
class InvoicePrinter {
    public void print(Invoice invoice) {
        System.out.println("Invoice amount: " + invoice.getAmount());
    }
}

// ===== OCP: Open/Closed Principle =====
abstract class Shape {
    abstract double area();
}

class Rectangle extends Shape {
    private double width, height;

    public Rectangle(double w, double h) {
        this.width = w;
        this.height = h;
    }

    @Override
    double area() {
        return width * height;
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(double r) {
        this.radius = r;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

// ===== LSP: Liskov Substitution Principle =====
class Bird {
    public void fly() {
        System.out.println("Flying");
    }
}

class Duck extends Bird {
    @Override
    public void fly() {
        System.out.println("Duck flying");
    }
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches can't fly");
    }
}

// ===== ISP: Interface Segregation Principle =====
interface Workable {
    void work();
}

interface Feedable {
    void eat();
}

class HumanWorker implements Workable, Feedable {
    @Override
    public void work() {
        System.out.println("Human working");
    }

    @Override
    public void eat() {
        System.out.println("Human eating");
    }
}

class RobotWorker implements Workable {
    @Override
    public void work() {
        System.out.println("Robot working");
    }
}

// ===== DIP: Dependency Inversion Principle =====
interface MessageService {
    void sendMessage(String msg);
}

class EmailService implements MessageService {
    public void sendMessage(String msg) {
        System.out.println("Email sent: " + msg);
    }
}

class Notification {
    private MessageService service;

    public Notification(MessageService service) {
        this.service = service;
    }

    public void notifyUser(String msg) {
        service.sendMessage(msg);
    }
}

// ===== Main Class =====
public class SOLIDDemo {
    public static void main(String[] args) {
        // SRP
        Invoice invoice = new Invoice(500);
        InvoicePrinter printer = new InvoicePrinter();
        printer.print(invoice);

        System.out.println("------");

        // OCP
        Shape rect = new Rectangle(5, 10);
        Shape circle = new Circle(3);
        System.out.println("Rectangle area: " + rect.area());
        System.out.println("Circle area: " + circle.area());

        System.out.println("------");

        // LSP
        Bird duck = new Duck();
        duck.fly();
        // Ostrich ostrich = new Ostrich(); ostrich.fly(); // would throw exception, violates LSP

        System.out.println("------");

        // ISP
        HumanWorker human = new HumanWorker();
        RobotWorker robot = new RobotWorker();
        human.work();
        human.eat();
        robot.work();

        System.out.println("------");

        // DIP
        MessageService email = new EmailService();
        Notification notification = new Notification(email);
        notification.notifyUser("SOLID principle demo");
    }
}
