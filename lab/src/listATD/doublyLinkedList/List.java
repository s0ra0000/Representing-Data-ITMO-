package listATD.doublyLinkedList; // Объявление пакета listATD.arrayList.doublyLinkedList

import exceptions.InvalidException; // Импорт класса исключения InvalidPositionException
import utils.Data; // Импорт класса Data

// Объявление класса DoublyLinkedList, реализующего интерфейс IList
public class List implements IList{
    protected static class Node {
        private Data data; // Объявление переменной data, доступной публично, для хранения данных
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
        // Проверяем, совпадают ли голова и хвост списка
        if (head == tail) {
            // Если список пуст, то создаем новый узел и делаем его головой и хвостом списка
            if (head == null) {
                head = new Node(x);
                tail = head;
                return;
            }
            // Если в списке только один элемент, создаем новый узел после головы
            head.next = new Node(head.data);
            head.data = x; // Помещаем новые данные в голову
            tail = head.next; // Обновляем хвост
            tail.prev = head; // Устанавливаем предыдущий элемент для хвоста
            return;
        }
        // Если позиция для вставки не определена, добавляем новый узел в конец списка
        if(p.node == null) {
            Node newNode = new Node(x);
            tail.next = newNode; // Связываем старый хвост с новым узлом
            newNode.prev = tail; // Устанавливаем предыдущий элемент для нового узла
            tail = newNode; // Обновляем хвост
            return;
        }
        Node current = head;
        // Проходим по списку до нужной позиции или до конца списка
        while(current != null && p.node != current){
            current = current.next;
        }
        // Если нужный узел не найден, выходим из метода
        if(current != null) return;
        Node tmp = p.node; // Сохраняем ссылку на узел, после которого нужно вставить новый элемент
        Node next = tmp.next; // Сохраняем ссылку на следующий узел после "tmp"
// Создаем новый узел с данными текущего узла "tmp" и вставляем его между "tmp" и "next"
        tmp.next = new Node(tmp.data);
        tmp.data = x; // Заменяем данные в узле "tmp" на новые данные "x"
// Устанавливаем связь между новым узлом и узлом "next"
        tmp.next.next = next; // Новый узел теперь указывает на "next"
        tmp.next.prev = tmp; // Устанавливаем обратную связь от нового узла к "tmp"
        if (next != null) next.prev = tmp.next; // Обновляем обратную связь у "next", если он не null, указывая на новый узел

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
        throw new InvalidException("Invalid position"); // Возвращаем null, если позиция невалидна
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
        throw new InvalidException("Invalid position"); // Генерация исключения, если следующего узла нет
    }

    @Override
    public Position previous(Position p) {
        if (p != null && p.node != null && p.node.prev != null) { // Если позиция валидна и у узла есть предыдущий
            return new Position(p.node.prev); // Возвращаем позицию предыдущего узла
        }
        throw new InvalidException("Invalid position"); // Возвращаем null, если предыдущего узла нет
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
