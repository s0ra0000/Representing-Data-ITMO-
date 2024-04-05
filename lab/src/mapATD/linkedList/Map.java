package mapATD.linkedList; // Объявление пакета

import utils.Data; // Импорт класса Data

public class Map { // Объявление класса Map
    public static class Node { // Вложенный статический класс для узлов связного списка
        Data data; // Данные, хранящиеся в узле
        Node next; // Ссылка на следующий узел

        public Node(Data data, Node next) { // Конструктор узла
            this.data = data; // Инициализация данных
            this.next = next; // Установка ссылки на следующий узел
        }
    }
    private Node head; // Голова списка, первый узел

    public Map() { // Конструктор по умолчанию
        this.head = null; // Инициализация головы списка как null
    }

    public void makenull() { // Метод для очистки карты
        head = null; // Установка головы списка в null
    }

    public void assign(char[] name, char[] address) { // Метод для добавления или обновления пары "имя-адрес"
        if (head == null) { // Если список пуст
            head = new Node(new Data(address, name), null); // Создание нового узла в качестве головы списка
            return;
        }
        Node node = findByName(name); // Поиск узла по имени

        if (node != null) { // Если узел найден
            node.data.setAddress(address); // Обновление адреса в найденном узле
            return;
        }
        head.next = new Node(new Data(address, name), head.next); // Добавление нового узла после головы списка
    }

    public boolean compute(char[] address, char[] name) { // Метод для обновления адреса по имени
        Node node = findByName(name); // Поиск узла по имени
        if (node == null) { // Если узел не найден
            return false; // Возвращаем false
        } else { // Если узел найден
            node.data.setAddress(address); // Обновление адреса
            return true; // Возвращаем true
        }
    }

    private Node findByName(char[] name) { // Вспомогательный метод для поиска узла по имени
        Node current = head; // Начало поиска с головы списка
        while (current != null) { // Пока не достигнут конец списка
            if (compareCharArrays(current.data.getName(), name)) { // Если имена совпадают
                return current; // Возвращаем найденный узел
            }
            current = current.next; // Переход к следующему узлу
        }
        return null; // Возвращаем null, если узел не найден
    }

    public void print() { // Метод для вывода всех пар "адрес-имя"
        Node current = head; // Начало с головы списка
        while (current != null) { // Пока не достигнут конец списка
            System.out.println(current.data);
            current = current.next; // Переход к следующему узлу
        }
    }
    private boolean compareCharArrays(char[] q1, char[] q2){
        int len = Math.min(q1.length, q2.length);
        for (int i = 0; i < len; i++){
            if (q1[i] == '0' || q2[i] == '0') continue;
            if (q1[i] != q2[i]) return false;
        }
        return true;
    }

}
