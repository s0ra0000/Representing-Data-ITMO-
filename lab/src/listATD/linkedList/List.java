package listATD.linkedList; // Объявление пакета

import exceptions.InvalidPositionException; // Импорт класса InvalidPositionException из пакета exceptions
import utils.Data; // Импорт класса Data из пакета utils

public class List implements IList{ // Определение класса LinkedList, реализующего интерфейс IList
    private Node head; // Приватное поле head, представляющее начальный элемент списка

    public List() {
        this.head = null; // Конструктор класса LinkedList, инициализирует начальный элемент списка как null
    }

    @Override
    public Position end() {
        return new Position(null); // Метод end() возвращает позицию после последнего элемента, представленную как Position с null-значением node
    }

    @Override
    public void insert(Data x, Position p) {
        // Создание нового узла с данными x
        Node newNode = new Node(x);

        // Проверка, если список пуст, вставить новый узел в начало
        if (head == null) {
            head = newNode; // Установка нового узла в качестве головы списка
            return;        // Возвращение из метода
        }

        // Проверка, если позиция p не указана, вставить в конец списка
        if (p == null || p.node == null) {
            Node last = getLast(); // Получение последнего узла списка
            last.next = newNode;   // Добавление нового узла в конец списка
            return;                // Возвращение из метода
        }

        // Вставка в начало списка, если p.node является головой списка
        if (p.node == head) {
            newNode.next = head; // Новый узел указывает на текущую голову
            head = newNode;      // Обновление головы списка на новый узел
            return;              // Возвращение из метода
        }

        // Вставка в середину списка
        Position previous = getPrevious(p); // Получение узла, предшествующего p
        if (previous.node == null) {
            return; // Возвращение из метода, если предыдущий узел не найден (неверная позиция)
        }

        // Вставка нового узла между предыдущим узлом и p.node
        newNode.next = p.node;          // Новый узел указывает на узел в позиции p
        previous.node.next = newNode;   // Предыдущий узел теперь указывает на новый узел
    }


    public Position first() {
        if (head == null) {
            return new Position(null); // Если список пуст, вернуть позицию после последнего элемента
        }
        return new Position(head); // Вернуть позицию первого узла в списке
    }

    @Override
    public void printlist() {
        Node current = head; // Начать с начального элемента списка
        while (current != null) { // Пока не достигнут конец списка
            System.out.println(current.data); // Вывести данные текущего элемента на экран
            current = current.next; // Перейти к следующему элементу
        }
    }

    @Override
    public Position locate(Data x) {
        Node current = head; // Начать с начального элемента списка
        while (current != null) { // Пока не достигнут конец списка
            if (current.data.equals(x)) { // Если данные текущего элемента равны x
                return new Position(current); // Вернуть позицию текущего элемента
            }
            current = current.next; // Перейти к следующему элементу
        }
        return null; // Если данные не найдены, вернуть null
    }

    @Override
    public Data retrieve(Position p) {
        if (p == null || p.node == null) {
            // Позиция p равна END(L) или недопустима: выбросить исключение
            throw new InvalidPositionException("Invalid position");
        }
        return p.node.data; // Вернуть данные элемента, на который указывает позиция p
    }

    @Override
    public Position next(Position p) {
        if (p == null || p.node == null) {
            // Позиция p не в списке или равна END(L): выбросить исключение
            throw new InvalidPositionException("Invalid position");
        }

        if (p.node.next == null) {
            // Если следующий элемент равен null, это означает, что p - последняя позиция в списке,
            // и следующая(p, L) должна быть END(L), которую мы представляем как позицию с null-значением node
            return new Position(null);
        }

        return new Position(p.node.next); // Вернуть позицию следующего элемента после p
    }

    @Override
    public Position previous(Position p) {
        if (p == null || p.node == head) {
            // Позиция p - это первая позиция, END(L) или она не в списке: выбросить исключение
            throw new InvalidPositionException("Invalid position");
        }

        Position previousPosition = getPrevious(p); // Получить позицию предыдущего элемента перед p
        if (previousPosition.node == null) {
            // Если предыдущая позиция равна null, это означает, что p - первая позиция или недопустима
            throw new InvalidPositionException("Invalid position");
        }
        return previousPosition; // Вернуть позицию предыдущего элемента перед p
    }

    @Override
    public void delete(Position p) {
        // Проверка на недопустимую позицию или END(L)
        if (p == null || p.node == null || head == null) {
            return; // Ничего не делать, если позиция недопустима или равна END(L)
        }

        // Специальный случай: удаление первого узла
        if (p.node == head) {
            head = head.next; // Удалить первый элемент, переключив указатель head на следующий элемент
            return;
        }

        // Общий случай: удаление узла на позиции p
        Position previousPosition = getPrevious(p); // Получить позицию предыдущего элемента перед p
        if (previousPosition.node != null) {
            Node nodeNext = previousPosition.node.next; // Следующий элемент после предыдущей позиции
            previousPosition.node.next = nodeNext.next; // Пропустить удаляемый узел, связав предыдущий элемент с элементом, идущим после удаляемого
            p.node = previousPosition.node.next; // Перейти к следующему узлу после удаления
        }
        // Ничего не делать, если previousPosition равен null (т. е. p не найдено)
    }

    public void makenull() {
        head = null; // Очистить список, установив head в null
    }

    // Приватный метод getLast() для получения последнего элемента списка
    private Node getLast() {
        Node current = head; // Начать с начального элемента списка
        while (current.next != null) { // Пока не достигнут конец списка
            current = current.next; // Перейти к следующему элементу
        }
        return current; // Вернуть последний элемент
    }

    // Приватный метод getPrevious(Position p) для получения позиции предыдущего элемента перед p
    private Position getPrevious(Position p) {
        if (p.node == head) return new Position(null); // Если p - это первый элемент, вернуть позицию с null-значением node

        Node current = head; // Начать с начального элемента списка
        while (current != null && current.next != p.node) {
            current = current.next; // Перейти к следующему элементу, пока не найдено p
        }
        return new Position(current); // Вернуть позицию предыдущего элемента перед p
    }
}
