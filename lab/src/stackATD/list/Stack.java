package stackATD.list;

import listATD.arrayList.List;
import utils.Data;

public class Stack {
    private List list;

    public Stack() {
        list = new List();
    }

    public void makenull() {
        list.makenull();
    }

    public void push(Data x) {
        // Так как стек работает по принципу LIFO, новые элементы добавляются в начало списка.
        list.insert(x, list.first());
    }

    public Data top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        // Возвращает элемент в начале списка, который является вершиной стека.
        return list.retrieve(list.first());
    }

    public Data pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        // Удаляет и возвращает элемент в начале списка, который является вершиной стека.
        Data topElement = list.retrieve(list.first());
        list.delete(list.first());
        return topElement;
    }

    public boolean isEmpty() {
        // Стек считается пустым, если в списке нет элементов.
        return list.first().equals(list.end());
    }
}
