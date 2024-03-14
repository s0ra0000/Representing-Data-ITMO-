package stackATD.linkedList; // Объявление пакета

import utils.Data; // Импорт класса Data из пакета utils

public class Stack { // Объявление класса Stack
    private Node top; // Вершина стека

    private static class Node { // Внутренний класс для узла стека
        Data data; // Данные, хранящиеся в узле
        Node next; // Ссылка на следующий узел в стеке

        Node(Data data) { // Конструктор узла
            this.data = data; // Инициализация данных узла
            this.next = null; // Инициализация следующего узла как null
        }
    }

    public Stack() { // Конструктор стека
        top = null; // Инициализация вершины стека как null, указывая на пустой стек
    }

    public void makenull() { // Метод для очистки стека
        top = null; // Установка вершины стека в null, делая стек пустым
    }

    public Data top() { // Метод для получения данных с вершины стека без удаления
        if (isEmpty()) { // Проверка, пуст ли стек
            throw new IllegalStateException("Stack is empty"); // В случае пустого стека выбрасывается исключение
        }
        return top.data; // Возвращение данных верхнего узла
    }

    public Data pop() { // Метод для удаления и возвращения данных с вершины стека
        if (isEmpty()) { // Проверка, пуст ли стек
            throw new IllegalStateException("Stack is empty"); // В случае пустого стека выбрасывается исключение
        }
        Data data = top.data; // Сохранение данных верхнего узла
        top = top.next; // Сдвиг вершины стека на следующий узел
        return data; // Возвращение сохранённых данных
    }

    public void push(Data x) { // Метод для добавления данных на вершину стека
        Node newNode = new Node(x); // Создание нового узла с данными
        newNode.next = top; // Установка текущей вершины стека как следующего узла для нового узла
        top = newNode; // Установка нового узла как вершины стека
    }

    public boolean isEmpty() { // Метод для проверки, пуст ли стек
        return top == null; // Возвращает true, если стек пуст (вершина указывает на null)
    }
}
