package stackATD.array;

import utils.Data;

public class Stack {
    private Data[] stack;
    private int top;
    private static final int DEFAULT_SIZE = 10;

    public Stack() {
        stack = new Data[DEFAULT_SIZE];
        top = -1;
    }

    public void makenull() {
        top = -1;
    }

    public Data top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }

    public Data pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top--];
    }

    public void push(Data x) {
        if (isFull()) {
            throw new IllegalStateException("Stack is full");
        }
        stack[++top] = x;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == stack.length - 1;
    }
}
