package stackATD.linkedList;

import utils.Data;

public class Stack {
    private Node top;

    private static class Node {
        Data data;
        Node next;

        Node(Data data) {
            this.data = data;
            this.next = null;
        }
    }

    public Stack() {
        top = null;
    }

    public void makenull() {
        top = null;
    }

    public Data top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    public Data pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Data data = top.data;
        top = top.next;
        return data;
    }

    public void push(Data x) {
        Node newNode = new Node(x);
        newNode.next = top;
        top = newNode;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
