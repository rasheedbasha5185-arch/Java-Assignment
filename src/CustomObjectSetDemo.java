import java.util.*;

class Employee {
    int id;
    String name;

    // Constructor
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // For HashSet and LinkedHashSet: we need equals and hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;
        Employee emp = (Employee) obj;
        return id == emp.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "[" + id + ", " + name + "]";
    }
}

public class CustomObjectSetDemo {
    public static void main(String[] args) {

        // Sample Employee objects
        Employee e1 = new Employee(103, "Charlie");
        Employee e2 = new Employee(101, "Alice");
        Employee e3 = new Employee(104, "David");
        Employee e4 = new Employee(102, "Bob");

        // ================= TreeSet =================
        // TreeSet maintains order based on Comparator (employee id)
        TreeSet<Employee> treeSet = new TreeSet<>(Comparator.comparingInt(emp -> emp.id));
        treeSet.add(e1);
        treeSet.add(e2);
        treeSet.add(e3);
        treeSet.add(e4);

        System.out.println("TreeSet (sorted by employee ID): " + treeSet);

        // ================= HashSet =================
        HashSet<Employee> hashSet = new HashSet<>();
        hashSet.add(e1);
        hashSet.add(e2);
        hashSet.add(e3);
        hashSet.add(e4);

        System.out.println("HashSet (no order guaranteed): " + hashSet);

        // ================= LinkedHashSet =================
        LinkedHashSet<Employee> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(e1);
        linkedHashSet.add(e2);
        linkedHashSet.add(e3);
        linkedHashSet.add(e4);

        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);
    }
}
