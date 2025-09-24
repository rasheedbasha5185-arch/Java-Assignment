import java.util.*;

class Task {
    int id;
    String name;
    int priority; // higher value = higher priority

    public Task(int id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "[" + id + ", " + name + ", priority=" + priority + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Task)) return false;
        Task t = (Task) obj;
        return id == t.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

public class CustomPriorityQueueDemo {

    public static void main(String[] args) {
        // Step 1: Create a PriorityQueue with custom Comparator based on priority
        PriorityQueue<Task> pq = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.priority, t1.priority));

        // Step 2: Add tasks
        Task t1 = new Task(101, "Task A", 3);
        Task t2 = new Task(102, "Task B", 5);
        Task t3 = new Task(103, "Task C", 1);
        Task t4 = new Task(104, "Task D", 4);

        pq.add(t1);
        pq.add(t2);
        pq.add(t3);
        pq.add(t4);

        System.out.println("PriorityQueue after adding tasks: " + pq);

        // Step 3: Remove highest priority task
        Task removed = pq.poll();
        System.out.println("Removed highest priority task: " + removed);
        System.out.println("PriorityQueue now: " + pq);

        // Step 4: Update a task's priority (remove and re-insert)
        Task updatedTask = new Task(103, "Task C", 6); // updated priority
        pq.remove(t3); // remove old
        pq.add(updatedTask); // re-insert updated
        System.out.println("PriorityQueue after updating Task C's priority: " + pq);

        // Step 5: Peek highest priority task without removing
        System.out.println("Current highest priority task: " + pq.peek());
    }
}
