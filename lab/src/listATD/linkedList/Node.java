package listATD.linkedList;

import utils.Data;

public class Node {
    public Data data; // Объявление переменной data, доступной публично, для хранения данных
    Node next; // Объявление переменной next для ссылки на следующий узел в списке

    public Node(Data data) { // Конструктор класса, принимающий объект данных
        this.data = data; // Инициализация переменной data переданным объектом
        this.next = null; // Инициализация переменной next как null, указывая, что следующего узла пока нет
    }
}