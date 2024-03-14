package listATD.cursorList; // Объявление пакета listATD.arrayList.CursorList

import exceptions.InvalidException;
import utils.Data; // Импорт класса Data из пакета utils

public class List implements IList{ // Объявление класса listATD.cursorList, реализующего интерфейс IList
    private static class Node {
        private Data data;
        private int next;

        Node(Data data, int next){
            this.data = data;
            this.next = next;
        }
        Node(int next){
            this.next = next;
            this.data = null;
        }
    }
    private Node[] array; // Массив для хранения узлов списка
    private int capacity = 50; // Общая вместимость списка
    private final int FREE = -1; // Константа для обозначения "свободного" индекса
    private final Position SPACE;
    private int firstIndex = FREE; // Поле для хранения индекса первого элемента списка
    public List() { // Конструктор списка с указанием его вместимости
        this.array = new Node[capacity]; // Создание массива узлов заданной вместимости
        for (int i = 0; i < capacity-1; i++) { // Инициализация каждого элемента массива
            array[i] = new Node(i+1); // Создание узла с пустыми данными
        }
        array[capacity-1] =new Node(-1);
        SPACE = new Position(0);
    }

    @Override
    public Position end() { // Метод для получения позиции за последним элементом
        return new Position(-1); // Возвращение позиции, соответствующей размеру списка
    }

    @Override
    public void insert(Data x, Position p) { // Метод для вставки элемента в заданную позицию
        if( p.getIndex() > array.length) return; // Проверка на неверную позицию
        if (SPACE.index == FREE) return; // Если свободных индексов нет, прекратить выполнение
        if (firstIndex == FREE) {
            firstIndex = SPACE.index;
            SPACE.index = array[firstIndex].next;
            array[firstIndex] = new Node(x, FREE); // Предположим, что Node конструктор принимает Data и индекс следующего элемента

            return;
        }
        if (p.getIndex() == firstIndex) {
            int newFirstIndex = SPACE.index;
            SPACE.index = array[newFirstIndex].next;
            array[newFirstIndex] = new Node(x, firstIndex);
            firstIndex = newFirstIndex;
            return;
        }
        if (p.getIndex() == FREE || p.getIndex() == array.length) {
            int last = firstIndex;
            while (array[last].next != FREE) { // Ищем последний элемент
                last = array[last].next;
            }
            int newIndex = SPACE.index;
            SPACE.index = array[newIndex].next;
            array[newIndex] = new Node(x, FREE);
            array[last].next = newIndex; // Связываем последний элемент с новым
            return;
        }
        int prevIndex = findPrevious(p.getIndex());
        if (prevIndex == FREE) return; // Если предыдущий элемент не найден

        int newIndex = SPACE.index;
        SPACE.index = array[newIndex].next;
        array[newIndex] = new Node(x, array[prevIndex].next);
        array[prevIndex].next = newIndex;

    }

    @Override
    public Position locate(Data x) { // Метод для поиска позиции элемента с заданными данными
        int index = firstIndex; // Начало поиска с первого элемента
        while (index != FREE) { // Пока не достигнут конец списка
            if (array[index].data.equals(x)) { // Если найден элемент с данными x
                return new Position(index); // Возврат его позиции
            }
            index = array[index].next; // Переход к следующему элементу
        }
        return end(); // Если элемент не найден, возврат позиции за последним элементом
    }

    @Override
    public Data retrieve(Position p) { // Метод для получения данных элемента по его позиции
        if (p.getIndex() == FREE || p.getIndex() >= array.length) { // Проверка на корректность позиции
            throw new InvalidException("Invalid position"); // Выброс исключения при неверной позиции
        }
        if(p.getIndex() == firstIndex) return array[firstIndex].data;
        return array[p.index].data; // Возврат данных элемента
    }

    @Override
    public void delete(Position p) { // Метод для удаления элемента по позиции
        if (firstIndex == FREE) return; // Проверка на корректность позиции
        int targetIndex = p.getIndex(); // Индекс удаляемого элемента
        if (p.getIndex() == firstIndex) { // Случай удаления первого элемента
            int temp = firstIndex; // Сохраняем индекс удаляемого элемента
            firstIndex = array[firstIndex].next; // Обновляем первый элемент
            array[temp].next = SPACE.index; // Добавляем удаляемый элемент в список свободных
            SPACE.index = temp; // Обновляем индекс первого свободного элемента
            return;
        }

        int prevIndex = findPrevious(targetIndex); // Находим предыдущий элемент
        if (prevIndex == FREE) return; // Если предыдущий элемент не найден, выходим
        int nextIndex = array[prevIndex].next;
        if(nextIndex == FREE) return;
        array[prevIndex].next = array[nextIndex].next; // Обновляем ссылку у предыдущего элемента
        array[nextIndex].next = SPACE.index;
        SPACE.index = nextIndex; // Обновляем индекс первого свободного элемента
        p.index = array[prevIndex].next;


    }

    @Override
    public Position next(Position p) { // Метод для получения позиции следующего элемента
        if (p.getIndex() == FREE || p.getIndex() >= array.length) { // Проверка на корректность позиции
            throw new InvalidException("Invalid position"); // Выброс исключения при неверной позиции
        }
        if(p.index == firstIndex){
            return new Position(array[firstIndex].next);
        }
        return new Position(array[p.getIndex()].next); // Возврат позиции следующего элемента
    }

    @Override
    public Position previous(Position p) { // Метод для получения позиции предыдущего элемента
        if (p.getIndex() == FREE || p.getIndex() > array.length) { // Проверка на корректность позиции
            throw new InvalidException("Position out of bounds"); // Выброс исключения при неверной позиции
        }
        int prev = findPrevious(p.index);
        return new Position(prev); // Возврат позиции предыдущего элемента
    }


    @Override
    public void makenull() { // Метод для очистки списка
        if(firstIndex == FREE) return;
       array[findLast()].next= SPACE.index;
       SPACE.index = firstIndex;
       firstIndex = FREE;
    }

    @Override
    public Position first() { // Метод для получения позиции первого элемента
        return new Position(firstIndex); // Возврат позиции первого элемента
    }

    @Override
    public void printlist() { // Метод для вывода списка
        int index = firstIndex; // Начало с первого элемента
        while (index != FREE) { // Пока не достигнут конец списка
            if(array[index].data != null){
                System.out.print(array[index].data + "\n"); // Вывод данных текущего элемент
            }
            index = array[index].next; // Переход к следующему элементу
        }
        System.out.println(); // Переход на новую строку после вывода всего списка
    }

    // Дополнительные вспомогательные методы

    private int findPrevious(int position) { // Метод для поиска индекса предыдущего элемента
        if (position == 0 || firstIndex == FREE) { // Если позиция первая или список пуст
            return FREE; // Возврат значения FREE
        }
        int currentIndex = firstIndex; // Начало поиска с первого элемента
        int prevIndex = FREE; // Инициализация индекса предыдущего элемента
        while (currentIndex != FREE) { // Пока не достигнута заданная позиция
            if(position == currentIndex){
                return prevIndex;
            }
            prevIndex = currentIndex; // Обновление индекса предыдущего элемента
            currentIndex = array[currentIndex].next; // Переход к следующему элементу
        }
        return -1;
    }

    private int findLast(){
        int current = firstIndex;
        int last = -1;
        while(current != -1){
            last = current;
            current = array[current].next;
        }
        return last;
    }


}
