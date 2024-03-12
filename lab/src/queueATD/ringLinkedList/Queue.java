package queueATD.ringLinkedList;

import utils.Data;

import java.util.NoSuchElementException;

public class Queue {
    private Node rear;
    private int size;

    private static class Node {
        Data data;
        Node next;

        Node(Data data) {
            this.data = data;
            this.next = null;
        }
    }

    public Queue() {
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(Data data) {
        Node newNode = new Node(data);
        if (rear == null) {
            newNode.next = newNode;
        } else {
            newNode.next = rear.next;
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    public Data dequeue() {
        if (rear == null) throw new NoSuchElementException("Queue is empty");
        Node front = rear.next;
        if (rear == front) {
            rear = null;
        } else {
            rear.next = front.next;
        }
        size--;
        return front.data;
    }

    public Data front() {
        if (rear == null) throw new NoSuchElementException("Queue is empty");
        return rear.next.data;
    }

    public boolean isEmpty() {
        return rear == null;
    }

    public void makenull() {
        rear = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }
}
