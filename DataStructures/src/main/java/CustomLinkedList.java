public class CustomLinkedList {

    // ===== Node class =====
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // ===== Linked List Head =====
    private Node head;
    private int size;

    // ===== Constructor =====
    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // ===== Add at the end =====
    public void add(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // ===== Remove by value =====
    public boolean remove(int value) {
        if (head == null) return false;

        if (head.data == value) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data == value) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // ===== Print the linked list =====
    public void printList() {
        Node current = head;
        System.out.print("LinkedList: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // ===== Size of the list =====
    public int size() {
        return size;
    }

    // ===== Main method to test =====
    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();

        list.add(10);
        list.add(20);
        list.add(30);
        list.printList(); // LinkedList: 10 -> 20 -> 30 -> null

        list.remove(20);
        list.printList(); // LinkedList: 10 -> 30 -> null

        list.remove(10);
        list.printList(); // LinkedList: 30 -> null

        System.out.println("Size of list: " + list.size());
    }
}
