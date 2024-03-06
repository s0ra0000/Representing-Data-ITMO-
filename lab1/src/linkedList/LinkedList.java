package linkedList;
// Импортируем необходимые пакеты
import exceptions.InvalidPositionException;
import interfaces.InterfaceList;

// Объявляем класс LinkedList, который реализует интерфейс InterfaceList.
// Тип данных <U> ограничен тем, что он должен быть сравнимым.
public class LinkedList<U extends Comparable<U>> implements InterfaceList<U> {

    // Вложенный приватный класс для представления узла списка.
    private class Node {
        U data;  // Данные узла.
        Node next;  // Ссылка на следующий узел.

        // Конструктор узла, принимающий данные и инициализирующий следующий узел как null.
        Node(U data) {
            this.data = data;
            this.next = null;
        }
    }

    // Ссылка на первый узел списка.
    private Node head;

    // Метод, который возвращает позицию после последнего элемента.
    @Override
    public int end() {
        if (head == null) return 1;  // Если список пуст, возвращаем 1.
        Node current = head;  // Начинаем с первого узла.
        int length = 1;
        while (current.next != null) {  // Пока не достигнем последнего узла,
            current = current.next;  // двигаемся к следующему узлу.
            length++;
        }
        return length + 1;  // Возвращаем позицию после последнего элемента.
    }

    // Метод для вставки элемента x на позицию p.
    @Override
    public void insert(U x, int p) {
        if (p < 1) {  // Если позиция недопустима,
            throw new IndexOutOfBoundsException("Недопустимая позиция!");  // выбрасываем исключение.
        }
        Node newNode = new Node(x);  // Создаем новый узел с данными x.

        // Если позиция для вставки - начало списка.
        if (p == 1) {
            newNode.next = head;  // Новый узел указывает на текущий первый узел.
            head = newNode;  // Голова теперь указывает на новый узел.
            return;
        }
        Node current = head;  // Начинаем с первого узла.
        int index = 1;
        // Ищем позицию для вставки или узнаем, что такой позиции нет.
        while (current != null && index < p - 1) {
            current = current.next;  // Переходим к следующему узлу.
            index++;
        }

        // Если текущий узел null, значит позиция выходит за границы списка.
        if (current == null) {
            throw new IndexOutOfBoundsException("Недопустимая позиция!");
        }

        // Вставляем новый узел после текущего узла.
        newNode.next = current.next;
        current.next = newNode;
    }

    // Метод для поиска позиции элемента x в списке.
    @Override
    public int locate(U x) {
        Node current = head;  // Начинаем с первого узла.
        int index = 1;
        while (current != null) {  // Пока список не закончится,
            if (current.data.equals(x)) return index;  // Если нашли элемент, возвращаем его позицию.
            current = current.next;  // Иначе переходим к следующему узлу.
            index++;
        }
        return end();  // Если элемента нет, возвращаем позицию после последнего элемента.
    }

    // Метод для получения элемента списка по позиции.
    @Override
    public U retrieve(int p) {
        if (p < 1) throw new IndexOutOfBoundsException("Недопустимая позиция!");  // Проверка на допустимость позиции.
        Node current = head;  // Начинаем с первого узла.
        int index = 1;
        while (current != null && index < p) {  // Ищем нужный узел.
            current = current.next;
            index++;
        }
        if (current == null) throw new IndexOutOfBoundsException("Недопустимая позиция!");  // Если узел не найден, выбрасываем исключение.
        return current.data;  // Возвращаем данные найденного узла.
    }

    // Метод для удаления элемента из списка по позиции.
    @Override
    public void delete(int p) {
        if (p < 1 || head == null) return;  // Проверка на допустимость позиции и не пустой ли список.

        if (p == 1) {  // Если удаляем первый элемент,
            head = head.next;  // просто сдвигаем голову списка на следующий узел.
            return;
        }
        Node current = head;  // Начинаем с первого узла.
        int index = 1;
        while (current.next != null && index < p - 1) {  // Ищем узел перед удаляемым.
            current = current.next;
            index++;
        }
        if (current.next == null) return;  // Если узел не найден.
        current.next = current.next.next;  // Убираем узел из списка.
    }

    // Метод, который возвращает следующую за p позицию в списке.
    @Override
    public int next(int p) {
        Node current = head;  // Начинаем с первого узла.
        int index = 1;
        while (current != null && index < p) {  // Ищем нужный узел.
            current = current.next;
            index++;
        }
        if (current == null || current.next == null) return end();  // Если узел или следующий узел не найден, возвращаем end().
        return index + 1;  // Возвращаем следующую позицию.
    }

    // Метод, который возвращает позицию перед p в списке.
    @Override
    public int previous(int p) {
        if (p <= 1) throw new IndexOutOfBoundsException("Недопустимая позиция");  // Проверка на допустимость позиции.
        return p - 1;  // Возвращаем позицию перед p.
    }

    // Метод, который делает список пустым.
    @Override
    public void makeNull() {
        head = null;  // Устанавливаем голову списка в null.
    }

    // Метод, который возвращает первую позицию в списке.
    @Override
    public int first() {
        return 1;  // Всегда возвращает 1.
    }

    // Метод для вывода списка на печать.
    @Override
    public void printList() {
        Node current = head;  // Начинаем с первого узла.
        while (current != null) {  // Пока список не закончится,
            System.out.print(current.data + " -> ");  // выводим данные текущего узла и стрелку.
            current = current.next;  // Переходим к следующему узлу.
        }
        System.out.println("null");  // В конце выводим "null".
    }
}
