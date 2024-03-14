package queueATD.ringLinkedList; // Объявление пакета

import utils.Data; // Импорт класса Data из пакета utils

import java.util.NoSuchElementException; // Импорт класса исключения для обработки ситуаций с отсутствующим элементом

public class Queue { // Объявление класса Queue
    private Node rear; // Указатель на последний элемент в кольцевом связном списке

    private static class Node { // Внутренний класс для узлов кольцевого связного списка
        Data data; // Данные, хранящиеся в узле
        Node next; // Ссылка на следующий узел в списке

        Node(Data data) { // Конструктор узла
            this.data = data; // Инициализация данных узла
            this.next = null; // Инициализация следующего узла как null
        }
    }

    public Queue() { // Конструктор по умолчанию
        this.rear = null; // Инициализация последнего элемента как null, указывая на пустую очередь
    }

    public void enqueue(Data data) { // Метод для добавления элемента в очередь
        Node newNode = new Node(data); // Создание нового узла с данными
        if (rear == null) { // Если очередь пуста
            newNode.next = newNode; // Установка ссылки на самого себя, формирование кольца
        } else { // Если в очереди уже есть элементы
            newNode.next = rear.next; // Вставка нового узла в кольцо
            rear.next = newNode; // Обновление ссылки последнего узла на новый узел
        }
        rear = newNode; // Обновление указателя на последний элемент
    }

    public Data dequeue() { // Метод для удаления и возвращения элемента из начала очереди
        if (rear == null) throw new NoSuchElementException("Queue is empty"); // Если очередь пуста, выбрасывается исключение
        Node front = rear.next; // Получение узла, находящегося в начале очереди
        if (rear == front) { // Если в очереди всего один элемент
            rear = null; // Очистка очереди
        } else { // Если в очереди более одного элемента
            rear.next = front.next; // Исключение первого элемента из кольца
        }
        return front.data; // Возвращение данных удаленного элемента
    }

    public Data front() { // Метод для получения элемента из начала очереди без его удаления
        if (rear == null) throw new NoSuchElementException("Queue is empty"); // Если очередь пуста, выбрасывается исключение
        return rear.next.data; // Возврат данных элемента, находящегося в начале очереди
    }

    public boolean isEmpty() { // Метод для проверки, пуста ли очередь
        return rear == null; // Возвращает true, если очередь пуста
    }

    public void makenull() { // Метод для очистки очереди
        rear = null; // Установка указателя на последний элемент в null
    }

    public boolean isFull() { // Метод, всегда возвращающий false, так как в кольцевом связном списке понятие "полной очереди" отсутствует
        return false; // Кольцевой связный список теоретически не может быть полным
    }

}
