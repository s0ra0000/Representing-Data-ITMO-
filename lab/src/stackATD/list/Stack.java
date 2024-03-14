package stackATD.list; // Объявление пакета

import listATD.arrayList.List; // Импорт класса List из пакета listATD.arrayList
import utils.Data; // Импорт класса Data из пакета utils

public class Stack { // Объявление класса Stack
    private List list; // Объект списка, используемый для реализации стека

    public Stack() { // Конструктор по умолчанию
        list = new List(); // Инициализация списка
    }

    public void makenull() { // Метод для очистки стека
        list.makenull(); // Очистка списка
    }

    public void push(Data x) { // Метод для добавления элемента в стек
        // Так как стек работает по принципу LIFO, новые элементы добавляются в начало списка.
        list.insert(x, list.first()); // Вставка элемента в начало списка
    }

    public Data top() { // Метод для получения верхнего элемента стека без его удаления
        if (isEmpty()) { // Проверка, пуст ли стек
            throw new IllegalStateException("Stack is empty"); // Генерация исключения, если стек пуст
        }
        // Возвращает элемент в начале списка, который является вершиной стека.
        return list.retrieve(list.first()); // Получение данных верхнего элемента
    }

    public Data pop() { // Метод для удаления и возвращения верхнего элемента стека
        if (isEmpty()) { // Проверка, пуст ли стек
            throw new IllegalStateException("Stack is empty"); // Генерация исключения, если стек пуст
        }
        // Удаляет и возвращает элемент в начале списка, который является вершиной стека.
        Data topElement = list.retrieve(list.first()); // Получение верхнего элемента
        list.delete(list.first()); // Удаление верхнего элемента из списка
        return topElement; // Возвращение данных удаленного элемента
    }

    public boolean isEmpty() { // Метод для проверки, пуст ли стек
        // Стек считается пустым, если в списке нет элементов.
        return list.first().equals(list.end()); // Проверка, равен ли первый элемент концу списка (условие пустоты)
    }
}
