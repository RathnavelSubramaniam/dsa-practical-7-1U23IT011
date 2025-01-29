import java.util.Scanner;

public class LinkedList {

    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head = null;
    Node tail = null;

    public void addNode(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void insertMiddle(int data, int position) {
        Node newNode = new Node(data);

        // If the position is invalid (less than 1 or greater than the list size)
        if (position < 1 || position > countNodes()) {
            System.out.println("Invalid position for insertion.");
            return;
        }

        // If inserting at the beginning
        if (position == 1) {
            newNode.next = head;
            head = newNode;
            return;
        }

        // If inserting at the end
        if (position == countNodes()) {
            tail.next = newNode;
            tail = newNode;
            return;
        }

        // Inserting in the middle
        Node current = head;
        for (int i = 1; i < position - 1; i++) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    public int countNodes() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void deleteNode(int position) {
        // If the position is invalid (less than 1 or greater than the list size)
        if (position < 1 || position > countNodes()) {
            System.out.println("Invalid position for deletion.");
            return;
        }

        // If deleting the first node
        if (position == 1) {
            head = head.next;
            if (head == null) {
                tail = null; // If the list becomes empty
            }
            return;
        }

        // If deleting the last node
        if (position == countNodes()) {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
            return;
        }

        // Deleting a node in the middle
        Node current = head;
        for (int i = 1; i < position - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Scanner sc = new Scanner(System.in);
        int pos = 0;
        int data, data1;

        System.out.println("ENTER INTEGERS TO CREATE NODES (ENTER A NON-INTEGER TO EXIT):");
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                data = sc.nextInt();
                list.addNode(data);
            } else {
                String temp = sc.next();
                System.out.println("LINKED LIST CREATED");
                System.out.println("LINKED LIST BEFORE INSERTING/DELETING");
                list.displayList();

                System.out.println("1. Insert\n2. Delete");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                if (choice == 1) {
                    System.out.println("ENTER THE POSITION WHERE YOU WANT TO INSERT");
                    if (sc.hasNextInt()) {
                        pos = sc.nextInt();

                        System.out.println("ENTER THE ELEMENT TO BE INSERTED:");
                        if (sc.hasNextInt()) {
                            data1 = sc.nextInt();
                            list.insertMiddle(data1, pos);
                            System.out.println("LINKED LIST AFTER INSERTING");
                            list.displayList();
                        } else {
                            System.out.println("Enter integers only");
                            System.exit(0);
                        }
                    } else {
                        System.out.println("Enter integers only");
                        System.exit(0);
                    }
                } else if (choice == 2) {
                    System.out.println("ENTER THE POSITION WHERE YOU WANT TO DELETE");
                    if (sc.hasNextInt()) {
                        pos = sc.nextInt();
                        list.deleteNode(pos);
                        System.out.println("LINKED LIST AFTER DELETING");
                        list.displayList();
                    } else {
                        System.out.println("Enter integers only");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Invalid choice.");
                    System.exit(0);
                }
            }
        }
    }

    public void displayList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}