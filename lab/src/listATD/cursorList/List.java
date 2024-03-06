package listATD.cursorList; // Объявление пакета listATD.arrayList.CursorList

import exceptions.InvalidPositionException;
import utils.Data; // Импорт класса Data из пакета utils

public class List implements IList{ // Объявление класса listATD.cursorList, реализующего интерфейс IList
    private Node[] array; // Массив для хранения узлов списка
    private int size; // Текущее количество элементов в списке
    private int capacity = 50; // Общая вместимость списка
    private final int FREE = -1; // Константа для обозначения "свободного" индекса

    public List() { // Конструктор списка с указанием его вместимости
        this.array = new Node[capacity]; // Создание массива узлов заданной вместимости
        this.size = 0; // Инициализация размера списка как 0
        for (int i = 0; i < capacity-1; i++) { // Инициализация каждого элемента массива
            array[i] = new Node(i+1); // Создание узла с пустыми данными
        }
        array[capacity-1] =new Node(-1);
    }

    @Override
    public Position end() { // Метод для получения позиции за последним элементом
        return new Position(size); // Возвращение позиции, соответствующей размеру списка
    }

    @Override
    public void insert(Data x, Position p) { // Метод для вставки элемента в заданную позицию
        if( p.getIndex() > size) return; // Проверка на неверную позицию
        if (size >= capacity ) resizeArray(); // Проверка на переполнение
        int freeIndex = findFreeIndex(); // Поиск свободного индекса для нового элемента
        if (freeIndex == FREE) return; // Если свободных индексов нет, прекратить выполнение

        array[freeIndex].data = x; // Вставка данных в найденный свободный узел
        if (p.getIndex() < size) { // Если позиция в пределах существующих элементов
            if (p.getIndex() == 0) { // Случай вставки в начало списка
                array[freeIndex].next = findFirst(); // Новый узел указывает на текущий первый элемент
                updateFirst(freeIndex); // Обновление указателя на первый элемент
            } else { // Вставка в середину списка
                int prevIndex = findPrevious(p.getIndex()); // Поиск предыдущего узла
                array[freeIndex].next = array[prevIndex].next; // Новый узел указывает на следующий за предыдущим
                array[prevIndex].next = freeIndex; // Предыдущий узел теперь указывает на новый
            }
        } else { // Вставка в конец списка
            if (size == 0) { // Если список был пуст
                updateFirst(freeIndex); // Новый узел становится первым
            } else { // Если в списке уже есть элементы
                int lastIndex = findPrevious(size); // Поиск последнего узла
                array[lastIndex].next = freeIndex; // Последний узел теперь указывает на новый
            }
        }
        size++; // Увеличение размера списка
    }

    @Override
    public Position locate(Data x) { // Метод для поиска позиции элемента с заданными данными
        int index = findFirst(); // Начало поиска с первого элемента
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
        if (p.getIndex() < 0 || p.getIndex() >= size) { // Проверка на корректность позиции
            throw new InvalidPositionException("Invalid position"); // Выброс исключения при неверной позиции
        }
        int index = findIndexByPosition(p.getIndex()); // Нахождение индекса элемента в массиве
        return array[index].data; // Возврат данных элемента
    }

    @Override
    public void delete(Position p) { // Метод для удаления элемента по позиции
        if (p.getIndex() < 0 || p.getIndex() >= size) return; // Проверка на корректность позиции
        if (p.getIndex() == 0) { // Случай удаления первого элемента
            int firstIndex = findFirst(); // Нахождение первого элемента
            updateFirst(array[firstIndex].next); // Обновление указателя на первый элемент
        } else { // Удаление элемента из середины или конца списка
            int prevIndex = findPrevious(p.getIndex()); // Нахождение предыдущего элемента
            int currentIndex = array[prevIndex].next; // Текущий элемент для удаления
            array[prevIndex].next = array[currentIndex].next; // Перенаправление указателя предыдущего элемента
        }
        size--; // Уменьшение размера списка
    }

    @Override
    public Position next(Position p) { // Метод для получения позиции следующего элемента
        if (p.getIndex() < 0 || p.getIndex() >= size) { // Проверка на корректность позиции
            throw new InvalidPositionException("Invalid position"); // Выброс исключения при неверной позиции
        }
        return new Position(p.getIndex() + 1); // Возврат позиции следующего элемента
    }

    @Override
    public Position previous(Position p) { // Метод для получения позиции предыдущего элемента
        if (p.getIndex() <= 0 || p.getIndex() > size) { // Проверка на корректность позиции
            throw new InvalidPositionException("Position out of bounds"); // Выброс исключения при неверной позиции
        }
        return new Position(p.getIndex() - 1); // Возврат позиции предыдущего элемента
    }

    @Override
    public void makenull() { // Метод для очистки списка
        for (int i = 0; i < capacity; i++) { // Проход по всем элементам массива
            array[i].data = null; // Удаление данных узла
            array[i].next = FREE; // Установка указателя на следующий элемент как FREE
        }
        size = 0; // Сброс размера списка
    }

    @Override
    public Position first() { // Метод для получения позиции первого элемента
        if (size == 0) { // Если список пуст
            return end(); // Возврат позиции за последним элементом
        }
        return new Position(0); // Возврат позиции первого элемента
    }

    @Override
    public void printlist() { // Метод для вывода списка
        int index = findFirst(); // Начало с первого элемента
        while (index != FREE) { // Пока не достигнут конец списка
            if(array[index].data != null){
                System.out.print(array[index].data + "\n"); // Вывод данных текущего элемент
            }
            index = array[index].next; // Переход к следующему элементу
        }
        System.out.println(); // Переход на новую строку после вывода всего списка
    }

    // Дополнительные вспомогательные методы
    private int findFreeIndex() { // Метод для поиска свободного индекса в массиве
        for (int i = 0; i < capacity; i++) { // Проход по всем индексам массива
            if (array[i].data == null) return i; // Если узел свободен, возврат его индекса
        }
        return FREE; // Если свободных узлов нет, возврат константы FREE
    }

    private int firstIndex = FREE; // Поле для хранения индекса первого элемента списка

    private int findFirst() { // Метод для получения индекса первого элемента
        return firstIndex; // Возврат индекса первого элемента
    }

    private void updateFirst(int index) { // Метод для обновления индекса первого элемента
        firstIndex = index; // Установка нового значения индекса первого элемента
    }

    private int findPrevious(int position) { // Метод для поиска индекса предыдущего элемента
        if (position == 0 || firstIndex == FREE) { // Если позиция первая или список пуст
            return FREE; // Возврат значения FREE
        }
        int currentIndex = firstIndex; // Начало поиска с первого элемента
        int prevIndex = FREE; // Инициализация индекса предыдущего элемента
        int currentPos = 0; // Счетчик текущей позиции
        while (currentIndex != FREE && currentPos < position) { // Пока не достигнута заданная позиция
            prevIndex = currentIndex; // Обновление индекса предыдущего элемента
            currentIndex = array[currentIndex].next; // Переход к следующему элементу
            currentPos++; // Увеличение счетчика позиции
        }
        return prevIndex; // Возврат индекса предыдущего элемента
    }

    private int findIndexByPosition(int position) { // Метод для поиска индекса элемента по его позиции в списке
        if (position < 0 || position >= size) { // Если позиция вне допустимого диапазона
            return FREE; // Возврат значения FREE
        }
        int currentIndex = firstIndex; // Начало поиска с первого элемента
        int currentPos = 0; // Счетчик текущей позиции
        while (currentIndex != FREE && currentPos < position) { // Пока не достигнута заданная позиция
            currentIndex = array[currentIndex].next; // Переход к следующему элементу
            currentPos++; // Увеличение счетчика позиции
        }
        return currentIndex; // Возврат индекса элемента
    }
    private void resizeArray() {
        int newCapacity = capacity * 2;
        Node[] newArray = new Node[newCapacity];
        System.arraycopy(array, 0, newArray, 0, capacity);
        for (int i = capacity; i < newCapacity; i++) {
            newArray[i] = new Node(null);
            newArray[i].next = FREE;
        }
        array = newArray;
        capacity = newCapacity;
    }
}
