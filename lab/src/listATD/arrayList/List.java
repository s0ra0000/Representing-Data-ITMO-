package listATD.arrayList; // Объявление пакета

import exceptions.InvalidException; // Импорт класса InvalidPositionException из пакета exceptions
import utils.Data; // Импорт класса Data из пакета utils

public class List implements IList { // Объявление класса ArrayList, который реализует интерфейс IList
    private Data[] array = new Data[20]; // Объявление массива данных типа Data
    private int size; // Объявление переменной для хранения размера списка (последный занят)

    // Конструктор класса ArrayList, принимает начальную емкость списка
    public List() {
        size = -1; // Изначально размер списка равен 0 (последный занят)
    }

    // Метод, возвращающий позицию после последнего элемента
    public Position first() {
        return size > -1 ? new Position(0) : end(); // Если список не пуст, вернуть позицию 1, иначе вернуть позицию после последнего элемента
    }

    // Метод, возвращающий позицию после последнего элемента (1-based)
    @Override
    public Position end() {
        return new Position(size + 1); // Вернуть позицию после последнего элемента
    }

    // Метод для печати списка
    @Override
    public void printlist() {
        for (int i = 0; i <= size; i++) {
            System.out.println(array[i].toString()); // Вывести элементы списка на экран
        }
    }

    // Метод для вставки элемента в заданную позицию
    @Override
    public void insert(Data x, Position p) {
        int index = p.getIndex(); // Преобразовать позицию в 0-индексацию для внутреннего массива
        if (index < size || index > end().getIndex()) {
            return; // Если позиция недопустима, ничего не делать
        }
        for (int i = size+1; i > index; i--) {
            array[i] = array[i - 1]; // Сдвинуть элементы справа от позиции для вставки
        }
        array[index] = x; // Вставить новый элемент на указанную позицию
        size++; // Увеличить размер списка
    }

    @Override
    public Position locate(Data x) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(x)) { // Поиск элемента в списке
                return new Position(i);
            }
        }
        // Элемент не найден, вернуть позицию после последнего элемента
        return end();
    }

    @Override
    public Data retrieve(Position p) {
        int index = p.getIndex(); // Преобразовать позицию в 0-индексацию для внутреннего массива
        if (index >= 0 && index <=size) {
            return array[index]; // Вернуть элемент списка по указанной позиции
        } else {
            // Позиция выходит за границы списка или является позицией после последнего элемента
            throw new InvalidException("Invalid position: " + p.getIndex());
        }
    }

    @Override
    public void delete(Position p) {
        int index = p.getIndex();
        if (index >= 0 && index <=size) {
            for (int i = index; i <= size-1; i++) {
                array[i] = array[i + 1]; // Сдвинуть элементы справа от позиции для удаления
            }
            array[size] = null; // Очистить последний элемент
            size--; // Уменьшить размер списка
        }
        // Если позиция выходит за границы списка или является позицией после последнего элемента, ничего не делать
    }

    @Override
    public Position next(Position p) {
        int index = p.getIndex();
        if (index < 0 || index > size) {
            // Позиция выходит за границы списка или является позицией после последнего элемента
            throw new InvalidException("Invalid position: " + index);
        }
        return new Position(index+1); // Вернуть позицию следующего элемента
    }

    @Override
    public Position previous(Position p) {
        int index = p.getIndex();
        if (index <= 0 || index > size + 1) {
            // Позиция - это первый элемент или выходит за границы списка
            throw new InvalidException("Invalid position: " + index);
        }
        return new Position(index - 1); // Вернуть позицию предыдущего элемента
    }

    @Override
    public void makenull() {
        size = -1; // Установить размер списка в -1
    }

}
