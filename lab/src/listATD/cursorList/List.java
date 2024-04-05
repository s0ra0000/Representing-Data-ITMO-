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
    private final Node[] array; // Массив для хранения узлов списка
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
    public void insert(Data x, Position p) { // Переопределенный метод для вставки элемента x в позицию p
        // Если индекс позиции больше размера массива, выходим из метода
        // Если нет свободного места (индекс SPACE указывает на FREE), выходим из метода

        if(p == null || SPACE.index == FREE|| p.getIndex() > array.length) return;

        // Если список пуст (firstIndex указывает на FREE)
        if (firstIndex == FREE) {
            firstIndex = SPACE.index; // Устанавливаем firstIndex на первый свободный индекс
            SPACE.index = array[firstIndex].next; // Обновляем индекс свободного места на следующий свободный индекс
            array[firstIndex] = new Node(x, FREE); // Создаем новый узел на месте firstIndex
            return;
        }

        // Если позиция вставки указывает на конец списка
        if (p.getIndex() == FREE || p.getIndex() == array.length) {
            int last = findLast(); // Начинаем поиск с первого элемента
            int newIndex = SPACE.index; // Запоминаем индекс для нового элемента
            SPACE.index = array[newIndex].next; // Обновляем индекс свободного места
            array[newIndex] = new Node(x, FREE); // Создаем новый узел в конце списка
            array[last].next = newIndex; // Обновляем ссылку на следующий элемент последнего узла
            return;
        }
        // Если позиция вставки не является первым элементом списка
        if(p.getIndex() != firstIndex){
            int prevIndex = findPrevious(p.getIndex()); // Ищем индекс предыдущего элемента для позиции вставки
            if (prevIndex == FREE) return; // Если предыдущий элемент не найден, выходим из метода
            array[prevIndex].next = SPACE.index; // Устанавливаем следующий элемент для предыдущего на первый свободный индекс
        }

        int tmp = SPACE.index; // Запоминаем временный индекс для нового узла
        SPACE.index = array[tmp].next; // Обновляем индекс свободного места
        array[tmp] = new Node(array[p.getIndex()].data, array[p.getIndex()].next); // Вставляем копию существующего узла в новое свободное место
        array[p.getIndex()] = new Node(x,tmp); // Заменяем существующий узел на новый узел с данными x и указателем на копию
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
        if (p == null ||firstIndex == FREE) return; // Проверка на корректность позиции

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
