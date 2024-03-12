package queueATD.array;

import utils.Data;

import java.util.NoSuchElementException;

public class Queue {
    private final Data[] queue;
    private int front = 0;
    private int rear = -1;
    private int size = 0;
    private final int capacity = 10;

    public Queue() {
        this.queue = new Data[capacity];
    }

    public void makenull() {
        front = 0;
        rear = -1;
        size = 0;
    }

    public Data front() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return queue[front];
    }

    public Data dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        Data item = queue[front];
        queue[front] = null; // Help GC
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    public void enqueue(Data item) {
        if (isFull()) throw new IllegalStateException("Queue is full");
        rear = (rear + 1) % capacity;
        queue[rear] = item;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

}
