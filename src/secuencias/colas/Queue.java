package colas;

public class Queue {

    private Node first = null;
    private Node last = null;
    private int size = 0;

    public void enqueue(String value) {
        Node newNode = new Node(value);
        if (this.isEmpty()) {
            this.first = newNode;
        } else {
            this.last.setNext(newNode);
        }
        this.last = newNode;
        this.size++;
    }

    public String dequeue() {
        if (this.isEmpty()) {
            return null;
        }
        String value = this.first.getValue();
        this.first = this.first.getNext();
        if (this.first == null) {
            this.last = null;
        }
        this.size--;
        return value;
    }

    public String peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.first.getValue();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }
}
