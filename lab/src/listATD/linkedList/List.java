package listATD.linkedList; // Объявление пакета

import exceptions.InvalidException; // Импорт класса InvalidPositionException из пакета exceptions
import utils.Data; // Импорт класса Data из пакета utils

public class List implements IList{ // Определение класса LinkedList, реализующего интерфейс IList
    protected static class Node {
        private Data data; // Объявление переменной data, доступной публично, для хранения данных
        private Node next; // Объявление переменной next для ссылки на следующий узел в списке

        public Node(Data data) { // Конструктор класса, принимающий объект данных
            this.data = data; // Инициализация переменной data переданным объектом
            this.next = null; // Инициализация переменной next как null, указывая, что следующего узла пока нет
        }
    }
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
        // Проверка, если позиция p не указана
        if (p.node == null) {
            Node newNode = new Node(x); // Создание нового узла с данными x
            if (head == null) {
                head = newNode; // Если список пуст, новый узел становится головой списка
                return; // Выход из метода
            }
            Node last = getLast(); // Получение последнего узла списка
            last.next = newNode;   // Связывание последнего узла с новым, добавляя его в конец
            return;                // Выход из метода
        }
        if (p.node == head) {
            Node newNode = new Node(head.data);
            newNode.next = head.next;
            head.next = newNode;
            head.data = x;
            return;
        }

        // Вставка узла в указанную позицию (не в конец списка)
        Node previous = getPrevious(p); // Попытка найти узел, предшествующий указанной позиции p
        // Проверка на невозможность вставки: если не нашли предыдущего и p.node не является головой списка
        if (previous == null) {
            return; // Если предыдущий узел не найден и позиция не является началом списка, выходим из метода
        }

        // Создание и вставка нового узла в список с сохранением порядка
        Node tmp = p.node.next;              // Сохранение ссылки на следующий узел после p
        p.node.next = new Node(p.node.data); // Создание нового узла с данными текущего узла p и вставка его после узла p
        p.node.next.next = tmp;              // Установка связи вставленного узла со следующим узлом после p
        p.node.data = x;                     // Замена данных в узле p на новые данные x
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
            throw new InvalidException("Invalid position");
        }
        return p.node.data; // Вернуть данные элемента, на который указывает позиция p
    }

    @Override
    public Position next(Position p) {
        if (p == null || p.node == null) {
            // Позиция p не в списке или равна END(L): выбросить исключение
            throw new InvalidException("Invalid position");
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
            throw new InvalidException("Invalid position");
        }

        Node previousPosition = getPrevious(p); // Получить позицию предыдущего элемента перед p
        if (previousPosition == null) {
            // Если предыдущая позиция равна null, это означает, что p - первая позиция или недопустима
            throw new InvalidException("Invalid position");
        }
        return new Position(previousPosition); // Вернуть позицию предыдущего элемента перед p
    }

    @Override
    public void delete(Position p) {
        // Проверка на недопустимую позицию или END(L)
        if (p.node == null || head == null) {
            return; // Ничего не делать, если позиция недопустима или равна END(L)
        }

        // Специальный случай: удаление первого узла
        if (p.node == head) {
            head = head.next; // Удалить первый элемент, переключив указатель head на следующий элемент
            return;
        }

        // Общий случай: удаление узла на позиции p
        Node previousPosition = getPrevious(p); // Получить позицию предыдущего элемента перед p
        if(previousPosition == null) return;

        previousPosition.next = p.node.next;
        Node nodeNext = previousPosition.next; // Следующий элемент после предыдущей позиции
        previousPosition.next = nodeNext.next; // Пропустить удаляемый узел, связав предыдущий элемент с элементом, идущим после удаляемого
        p.node = previousPosition.next; // Перейти к следующему узлу после удаления
        // Ничего не делать, если previousPosition равен null (т. е. p не найдено)
    }

    public void makenull() {
        head = null; // Очистить список, установив head в null
    }

    // Приватный метод getLast() для получения последнего элемента списка
    private Node getLast() {
        Node current = head.next; // Начать с начального элемента списка
        Node prev = head;
        while (current != null) { // Пока не достигнут конец списка
            prev = current;
            current = current.next; // Перейти к следующему элементу
        }
        return prev; // Вернуть последний элемент
    }

    // Приватный метод getPrevious(Position p) для получения позиции предыдущего элемента перед p
    private Node getPrevious(Position p) {
        Node current = head.next; // Начать с начального элемента списка
        Node prev = head;
        while (current != null) {
            if(current == p.node){
                return prev;
            }
            prev = current;
            current = current.next; // Перейти к следующему элементу, пока не найдено p
        }
        return null;
    }
}
