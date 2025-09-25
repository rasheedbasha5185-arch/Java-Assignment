import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class MultiStack<T> {
    private final int stackCapacity;          // Fixed size of each stack
    private final List<Stack<T>> stacks;      // List to hold multiple stacks

    // Constructor: define capacity of each individual stack
    public MultiStack(int stackCapacity) {
        this.stackCapacity = stackCapacity;
        this.stacks = new ArrayList<>();
        stacks.add(new Stack<>());           // Start with one stack
    }

    // Push element into the latest stack
    public void push(T value) {
        Stack<T> last = getLastStack();
        if (last.size() == stackCapacity) {
            // If last stack is full, create a new stack
            Stack<T> newStack = new Stack<>();
            newStack.push(value);
            stacks.add(newStack);
        } else {
            last.push(value);
        }
    }

    // Pop element from the latest stack
    public T pop() {
        Stack<T> last = getLastStack();
        if (last.isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        T value = last.pop();

        // If last stack becomes empty, remove it (cleanup)
        if (last.isEmpty() && stacks.size() > 1) {
            stacks.remove(stacks.size() - 1);
        }
        return value;
    }

    // Helper: get last stack
    private Stack<T> getLastStack() {
        return stacks.get(stacks.size() - 1);
    }

    // Utility: Print all stacks
    public void printStacks() {
        for (int i = 0; i < stacks.size(); i++) {
            System.out.println("Stack " + (i + 1) + ": " + stacks.get(i));
        }
    }
}

public class MultiStackDemo {
    public static void main(String[] args) {
        MultiStack<Integer> multiStack = new MultiStack<>(3); // Each stack can hold 3 items

        // Push 10 elements -> should create multiple stacks
        for (int i = 1; i <= 10; i++) {
            multiStack.push(i);
        }

        System.out.println("After pushing 10 elements:");
        multiStack.printStacks();

        // Pop 4 elements -> should shrink stacks
        for (int i = 0; i < 4; i++) {
            System.out.println("Popped: " + multiStack.pop());
        }

        System.out.println("After popping 4 elements:");
        multiStack.printStacks();
    }
}
