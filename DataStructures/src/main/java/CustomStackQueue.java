// CustomStackQueue.java
public class CustomStackQueue {

    // ===== Node class for LinkedList =====
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // ===== LinkedList Implementation =====
    private static class CustomLinkedList {
        Node head;
        int size;

        public CustomLinkedList() {
            head = null;
            size = 0;
        }

        // Add at the beginning (for Stack push / Queue enqueue)
        public void addFirst(int value) {
            Node newNode = new Node(value);
            newNode.next = head;
            head = newNode;
            size++;
        }

        // Remove from beginning (for Stack pop / Queue dequeue)
        public int removeFirst() {
            if (head == null) throw new RuntimeException("List is empty");
            int val = head.data;
            head = head.next;
            size--;
            return val;
        }

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    // ===== Stack Implementation =====
    private static class CustomStack {
        CustomLinkedList list = new CustomLinkedList();

        public void push(int value) {
            list.addFirst(value); // Stack push adds at the top
        }

        public int pop() {
            return list.removeFirst(); // Stack pop removes from the top
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }
    }

    // ===== Queue Implementation =====
    private static class CustomQueue {
        CustomLinkedList list = new CustomLinkedList();
        Node tail = null;

        public void enqueue(int value) {
            Node newNode = new Node(value);
            if (list.head == null) {
                list.head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            list.size++;
        }

        public int dequeue() {
            if (list.head == null) throw new RuntimeException("Queue is empty");
            int val = list.head.data;
            list.head = list.head.next;
            if (list.head == null) tail = null; // Update tail if queue becomes empty
            list.size--;
            return val;
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }
    }

    // ===== Main Method to Test =====
    public static void main(String[] args) {
        System.out.println("==== Stack Operations ====");
        CustomStack stack = new CustomStack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Pop from Stack: " + stack.pop());
        System.out.println("Pop from Stack: " + stack.pop());

        System.out.println("\n==== Queue Operations ====");
        CustomQueue queue = new CustomQueue();
        queue.enqueue(100);
        queue.enqueue(200);
        queue.enqueue(300);
        System.out.println("Dequeue from Queue: " + queue.dequeue());
        System.out.println("Dequeue from Queue: " + queue.dequeue());
    }
}
