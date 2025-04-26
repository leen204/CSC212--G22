
package datastruproject;

public class LinkedList<T> {

    private static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T val) {
            data = val;
            next = null;
        }
    }

    private Node<T> head;
    private Node<T> current;

    public LinkedList() {
        head = current = null;
    }

    public boolean empty() {
        return head == null;
    }

    public boolean last() {
        return current != null && current.next == null;
    }

    public boolean full() {
        return false;
    }

    public void findFirst() {
        current = head;
    }

    public void findNext() {
        if (current != null)
            current = current.next;
    }

    public T retrieve() {
        if (current != null)
            return current.data;
        return null;
    }

    public void update(T val) {
        if (current != null)
            current.data = val;
    }

    public void insert(T val) {
        Node<T> tmp;
        if (empty()) {
            current = head = new Node<T>(val);
        } else {
            tmp = current.next;
            current.next = new Node<T>(val);
            current = current.next;
            current.next = tmp;
        }
    }

    public void remove() {
        if (empty() || current == null) return;

        if (current == head) {
            head = head.next;
            current = head;
        } else {
            Node<T> tmp = head;
            while (tmp.next != current) {
                tmp = tmp.next;
            }
            tmp.next = current.next;
            current = tmp.next != null ? tmp.next : head;
        }
    }

    public boolean find(T key) {
        Node<T> tmp = head;
        while (tmp != null) {
            if (tmp.data.equals(key)) {
                current = tmp;
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }
}
