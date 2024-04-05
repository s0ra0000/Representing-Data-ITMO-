package stackATD.linkedList; // Объявление пакета

import utils.Data; // Импорт класса Data из пакета utils

public class Stack { // Объявление класса Stack
    private Node top; // Вершина стека

    private static class Node { // Внутренний класс для узла стека
        Data data; // Данные, хранящиеся в узле
        Node next; // Ссылка на следующий узел в стеке

        //в параметре next
        Node(Data data, Node next) { // Конструктор узла
            this.data = data; // Инициализация данных узла
            this.next = next; // Инициализация следующего узла как null
        }
    }

    public Stack() { // Конструктор стека
        top = null; // Инициализация вершины стека как null, указывая на пустой стек
    }

    public void makenull() { // Метод для очистки стека
        top = null; // Установка вершины стека в null, делая стек пустым
    }

    public Data top() { // Метод для получения данных с вершины стека без удаления
        return top.data; // Возвращение данных верхнего узла
    }

    public Data pop() { // Метод для удаления и возвращения данных с вершины стека
        Data data = top.data; // Сохранение данных верхнего узла
        top = top.next; // Сдвиг вершины стека на следующий узел
        return data; // Возвращение сохранённых данных
    }

    public void push(Data x) { // Метод для добавления данных на вершину стека
        top = new Node(x,top); // Создание нового узла с данными
    }

    public boolean isEmpty() { // Метод для проверки, пуст ли стек
        return top == null; // Возвращает true, если стек пуст (вершина указывает на null)
    }
}
