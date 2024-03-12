package listATD.doublyLinkedList; // Объявление пакета listATD.arrayList.doublyLinkedList

import exceptions.InvalidPositionException; // Импорт класса исключения InvalidPositionException
import utils.Data; // Импорт класса Data

// Объявление класса DoublyLinkedList, реализующего интерфейс IList
public class List implements IList{
    protected static class Node {
        private final Data data; // Объявление переменной data, доступной публично, для хранения данных
        private Node next; // Объявление переменной next для ссылки на следующий узел в списке
        private Node prev;
        public Node(Data data) { // Конструктор класса, принимающий объект данных
            this.data = data; // Инициализация переменной data переданным объектом
            this.next = null; // Инициализация переменной next как null, указывая, что следующего узла пока нет
            this.prev = null;
        }
    }
    private Node head; // Объявление начального узла списка
    private Node tail; // Объявление конечного узла списка

    // Конструктор без параметров, инициализирует пустой список
    public List() {
        this.head = null; // Инициализация начального узла как null
        this.tail = null; // Инициализация конечного узла как null
    }

    @Override
    public Position end() {
        return new Position(null); // Возвращает позицию, обозначающую конец списка
    }

    @Override
    public void insert(Data x, Position p) {
        Node newNode = new Node(x); // Создание нового узла с данными x
        if(p.node==null){
            // Обновление конечного узла списка
            if(head == null){ // Проверка, пустой ли список
                head = newNode; // Назначение нового узла начальным
            }
            else{
                tail.next = newNode; // Связывание конечного узла с новым узлом
                newNode.prev = tail; // Установка предыдущего узла для нового узла
            }
            tail = newNode; // Назначение нового узла конечным
            return;
        }
        if(p.node == head){ // Вставка перед начальным узлом
            newNode.next = head; // Связывание нового узла со старым начальным узлом
            head.prev = newNode; // Установка предыдущего узла для старого начального узла
            head = newNode; // Обновление начального узла списка
            return;
        }
         // Вставка между узлами
        Node current = head; // Начало поиска с начала списка
        while(current != null && p.node != current){ // Поиск узла для вставки после него
            current = current.next; // Переход к следующему узлу
        }
        if(current == null) { // Если узел не найден, вставляем в конец
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        newNode.next = current; // Связывание нового узла с найденным
        newNode.prev = current.prev; // Установка предыдущего узла для нового
        current.prev.next = newNode; // Обновление следующего узла для предыдущего узла
        current.prev = newNode; // Обновление предыдущего узла для найденного узла

    }

    @Override
    public Position locate(Data x) {
        Node current = head; // Начало поиска с начала списка
        while (current != null) { // Пока не достигнут конец списка
            if (current.data.equals(x)) { // Если данные текущего узла совпадают с искомыми
                return new Position(current); // Возвращаем позицию текущего узла
            }
            current = current.next; // Переход к следующему узлу
        }
        return null; // Возвращаем null, если данные не найдены
    }

    @Override
    public Data retrieve(Position p) {
        if (p != null && p.node != null) { // Если позиция валидна
            return p.node.data; // Возвращаем данные узла
        }
        throw new InvalidPositionException("Invalid position"); // Возвращаем null, если позиция невалидна
    }

    @Override
    public void delete(Position p) {
        if (p != null && p.node != null) { // Если позиция валидна
            if (p.node == head) { // Удаление начального узла
                head = head.next; // Обновление начального узла
                if (head != null) {
                    head.prev = null; // Обнуление ссылки на предыдущий узел для нового начального узла
                    p.node = head; // Обновление позиции на новый начальный узел
                }
            }
            else if (p.node == tail) { // Удаление конечного узла
                tail = tail.prev; // Обновление конечного узла
                tail.next = null; // Обнуление ссылки на следующий узел для нового конечного узла
                p.node = null; // Обнуление позиции
            }
            else { // Удаление узла из середины списка
                p.node.prev.next = p.node.next; // Обновление ссылки на следующий узел для предыдущего узла
                if (p.node.next != null) {
                    p.node.next.prev = p.node.prev; // Обновление ссылки на предыдущий узел для следующего узла
                    p.node = p.node.prev.next; // Обновление позиции на следующий узел
                }
            }

        }
    }

    @Override
    public Position next(Position p) {
        if (p != null && p.node != null) { // Если позиция валидна
            return new Position(p.node.next); // Возвращаем позицию следующего узла
        }
        throw new InvalidPositionException("Invalid position"); // Генерация исключения, если следующего узла нет
    }

    @Override
    public Position previous(Position p) {
        if (p != null && p.node != null && p.node.prev != null) { // Если позиция валидна и у узла есть предыдущий
            return new Position(p.node.prev); // Возвращаем позицию предыдущего узла
        }
        throw new InvalidPositionException("Invalid position"); // Возвращаем null, если предыдущего узла нет
    }

    @Override
    public void makenull() {
        head = null; // Обнуление начального узла
        tail = null; // Обнуление конечного узла
    }

    @Override
    public Position first() {
        return new Position(head); // Возвращаем позицию первого узла списка
    }

    @Override
    public void printlist() {
        Node current = head; // Начало с начального узла списка
        while (current != null) { // Пока не достигнут конец списка
            System.out.println(current.data); // Вывод данных текущего узла
            current = current.next; // Переход к следующему узлу
        }
    }
}
