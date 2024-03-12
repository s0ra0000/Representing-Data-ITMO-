package listATD.arrayList; // Объявление пакета

import exceptions.InvalidPositionException; // Импорт класса InvalidPositionException из пакета exceptions
import listATD.arrayList.IList; // Импорт интерфейса IList из пакета listATD.arrayList.interfaces
import utils.Data; // Импорт класса Data из пакета utils

public class List implements IList { // Объявление класса ArrayList, который реализует интерфейс IList
    private Data[] array = new Data[20]; // Объявление массива данных типа Data
    private int size; // Объявление переменной для хранения размера списка (последный занят)

    // Конструктор класса ArrayList, принимает начальную емкость списка
    public List() {
        size = 0; // Изначально размер списка равен 0
    }

    // Метод, возвращающий позицию после последнего элемента
    public Position first() {
        return size > 0 ? new Position(1) : end(); // Если список не пуст, вернуть позицию 1, иначе вернуть позицию после последнего элемента
    }

    // Метод, возвращающий позицию после последнего элемента (1-based)
    @Override
    public Position end() {
        return new Position(size + 1); // Вернуть позицию после последнего элемента
    }

    // Метод для печати списка
    @Override
    public void printlist() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i].toString()); // Вывести элементы списка на экран
        }
    }

    // Метод для вставки элемента в заданную позицию
    @Override
    public void insert(Data x, Position p) {
        int index = p.getIndex() - 1; // Преобразовать позицию в 0-индексацию для внутреннего массива
        if (index > size || index < 0) {
            return; // Если позиция недопустима, ничего не делать
        }
        if (size >= array.length) {
            resize(); // Если размер списка достиг емкости массива, увеличить емкость
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1]; // Сдвинуть элементы справа от позиции для вставки
        }
        array[index] = x; // Вставить новый элемент на указанную позицию
        size++; // Увеличить размер списка
    }

    @Override
    public Position locate(Data x) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(x)) { // Поиск элемента в списке
                // Найден элемент, вернуть его позицию (1-based)
                return new Position(i + 1);
            }
        }
        // Элемент не найден, вернуть позицию после последнего элемента
        return end();
    }

    @Override
    public Data retrieve(Position p) {
        int index = p.getIndex() - 1; // Преобразовать позицию в 0-индексацию для внутреннего массива
        if (index >= 0 && index < size) {
            return array[index]; // Вернуть элемент списка по указанной позиции
        } else {
            // Позиция выходит за границы списка или является позицией после последнего элемента
            throw new InvalidPositionException("Invalid position: " + p.getIndex());
        }
    }

    @Override
    public void delete(Position p) {
        int index = p.getIndex() - 1; // Преобразовать позицию в 0-индексацию для внутреннего массива
        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1]; // Сдвинуть элементы справа от позиции для удаления
            }
            array[size - 1] = null; // Очистить последний элемент
            size--; // Уменьшить размер списка
        }
        // Если позиция выходит за границы списка или является позицией после последнего элемента, ничего не делать
    }

    @Override
    public Position next(Position p) {
        int index = p.getIndex();
        if (index < 1 || index > size) {
            // Позиция выходит за границы списка или является позицией после последнего элемента
            throw new InvalidPositionException("Invalid position: " + index);
        }
        return new Position(index + 1); // Вернуть позицию следующего элемента
    }

    @Override
    public Position previous(Position p) {
        int index = p.getIndex();
        if (index <= 1 || index > size + 1) {
            // Позиция - это первый элемент или выходит за границы списка
            throw new InvalidPositionException("Invalid position: " + index);
        }
        return new Position(index - 1); // Вернуть позицию предыдущего элемента
    }

    @Override
    public void makenull() {
        size = 0; // Установить размер списка в 0
    }

    // Вспомогательный метод для изменения размера массива
    private void resize() {
        Data[] newArray = new Data[array.length * 2]; // Создать новый массив большего размера
        System.arraycopy(array, 0, newArray, 0, array.length); // Скопировать элементы из старого массива в новый
        array = newArray; // Переприсвоить массиву ссылку на новый массив
    }
}
