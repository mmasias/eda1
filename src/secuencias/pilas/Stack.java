package pilas;

import listas.basica.Node;

public class Stack {

    private Node top = null;
    private int size = 0;

    public void push(String value) {
        Node newNode = new Node(value);
        newNode.setNext(this.top);
        this.top = newNode;
        this.size++;
    }

    public String pop() {
        if (this.isEmpty()) {
            return null;
        }
        String value = this.top.getValue();
        this.top = this.top.getNext();
        this.size--;
        return value;
    }

    public String peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.top.getValue();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }
}
