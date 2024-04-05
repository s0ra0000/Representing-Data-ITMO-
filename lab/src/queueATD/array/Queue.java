package queueATD.array; // Объявление пакета

import utils.Data; // Импорт класса Data из пакета utils

import java.util.NoSuchElementException; // Импорт класса исключений для отсутствующего элемента

public class Queue { // Объявление класса Queue
    private final Data[] queue; // Массив для хранения элементов очереди
    private int front = 0; // Индекс начала очереди

    private final int capacity = 10; // Емкость очереди
    private int rear = capacity - 1; // Индекс конца очереди, инициализированный как емкость минус один

    public Queue() { // Конструктор по умолчанию
        this.queue = new Data[capacity]; // Инициализация массива для очереди с заданной емкостью
    }

    public void makenull() { // Метод для очистки очереди
        front = 0; // Сброс индекса начала очереди
        rear = capacity - 1; // Сброс индекса конца очереди
    }

    public Data front() { // Метод для получения элемента из начала очереди без его удаления
        return queue[front]; // Возврат элемента на начале очереди
    }

    public Data dequeue() { // Метод для удаления и возвращения элемента из начала очереди
        Data item = queue[front]; // Получение элемента на начале очереди
        front = next(front); // Обновление индекса начала очереди
        return item; // Возврат удаленного элемента
    }

    public void enqueue(Data item) { // Метод для добавления элемента в конец очереди
        rear = next(rear); // Обновление индекса конца очереди
        queue[rear] = item; // Добавление элемента в конец очереди
    }

    public boolean isEmpty() { // Метод для проверки, пуста ли очередь
        return next(rear) == front; // Возвращает true, если очередь пуста
    }

    public boolean isFull() { // Метод для проверки, полна ли очередь
        return next(next(rear)) == front; // Возвращает true, если очередь полна
    }
    private int next(int from) {return (from + 1) % capacity;}

}
