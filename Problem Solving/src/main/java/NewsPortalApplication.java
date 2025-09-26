import java.util.ArrayList;
import java.util.List;

// Step 1: Observer Interface
interface Observer {
    void update(String news);
}

// Step 2: Subject Interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String news);
}

// Step 3: Concrete Subject (Publisher)
class NewsPublisher implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String news) {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }

    public void publishNews(String news) {
        System.out.println("\n[News Published]: " + news);
        notifyObservers(news);
    }
}

// Step 4: Concrete Observer (Subscriber)
class Subscriber implements Observer {
    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " received news update: " + news);
    }
}

// Step 5: Demo Class (Real-Time Application Name)
public class NewsPortalApplication {
    public static void main(String[] args) {
        NewsPublisher publisher = new NewsPublisher();

        // Create subscribers
        Subscriber alice = new Subscriber("Alice");
        Subscriber bob = new Subscriber("Bob");
        Subscriber charlie = new Subscriber("Charlie");

        // Register subscribers
        publisher.registerObserver(alice);
        publisher.registerObserver(bob);
        publisher.registerObserver(charlie);

        // Publish news events
        publisher.publishNews("Design Patterns are essential for large projects!");
        publisher.publishNews("Observer Pattern allows dynamic event subscription!");

        // Remove a subscriber dynamically
        publisher.removeObserver(bob);

        // Publish another event
        publisher.publishNews("Bob will not receive this update!");
    }
}
