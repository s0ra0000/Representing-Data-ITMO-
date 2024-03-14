package stackATD.array; // Объявление пакета

import utils.Data; // Импорт класса Data из пакета utils

public class Stack { // Объявление класса Stack
    private Data[] stack; // Массив для хранения элементов стека
    private int top; // Индекс верхнего элемента стека
    private static final int DEFAULT_SIZE = 10; // Константа для размера стека по умолчанию

    public Stack() { // Конструктор по умолчанию
        stack = new Data[DEFAULT_SIZE]; // Инициализация массива размером по умолчанию
        top = -1; // Инициализация индекса верхнего элемента как -1, что означает пустой стек
    }

    public void makenull() { // Метод для очистки стека
        top = -1; // Установка индекса верхнего элемента в -1 для обозначения пустого стека
    }

    public Data top() { // Метод для получения верхнего элемента без его удаления
        if (isEmpty()) { // Проверка, пуст ли стек
            throw new IllegalStateException("Stack is empty"); // В случае пустого стека выбрасывается исключение
        }
        return stack[top]; // Возвращение верхнего элемента стека
    }

    public Data pop() { // Метод для удаления и возвращения верхнего элемента стека
        if (isEmpty()) { // Проверка, пуст ли стек
            throw new IllegalStateException("Stack is empty"); // В случае пустого стека выбрасывается исключение
        }
        return stack[top--]; // Возвращение верхнего элемента стека и декремент индекса верхнего элемента
    }

    public void push(Data x) { // Метод для добавления элемента в стек
        if (isFull()) { // Проверка, полон ли стек
            throw new IllegalStateException("Stack is full"); // В случае полного стека выбрасывается исключение
        }
        stack[++top] = x; // Инкремент индекса верхнего элемента и добавление нового элемента
    }

    public boolean isEmpty() { // Метод для проверки, пуст ли стек
        return top == -1; // Возвращает true, если стек пуст, иначе false
    }

    public boolean isFull() { // Метод для проверки, полон ли стек
        return top == stack.length - 1; // Возвращает true, если стек полон, иначе false
    }
}
