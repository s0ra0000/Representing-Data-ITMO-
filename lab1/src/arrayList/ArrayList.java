package arrayList;

import exceptions.InvalidPositionException;
import interfaces.InterfaceList;

@SuppressWarnings("ALL")
public class ArrayList<U extends Comparable<U>> implements InterfaceList<U> {
    private final U[] elements; // Массив, хранящий элементы списка
    private int last; // Последняя заполненная позиция

    // Конструктор, инициализирующий список от массива
    public ArrayList(U[] elements) {
        this.elements = elements;
        last = 0;
    }

    // Метод возвращающий позицию после последнего элемента
    @Override
    public int end() {
        return last + 1;
    }


    // Метод вставляющий элемент x по позиции p
    @Override
    public void insert(U x, int p) {
        if (isPositionValid(p)) {
            shiftElementsForward(p);
            elements[p - 1] = x;
            last++;
        }
    }

    // Метод возвращающий позицию элемента x в списке
    @Override
    public int locate(U x) {
        for (int i = 0; i < last; i++) {
            if (elements[i].compareTo(x) == 0) {
                return i + 1;
            }
        }
        return end();
    }

    // Метод возвращающий элемент из списка по позиции
    @Override
    public U retrieve(int p) {
        if (isPositionValid(p)) {
            return elements[p - 1];
        }
        throw new InvalidPositionException("Недопустимая позиция!");
    }

    // Метод удаляющий элемент из списка по позиции
    @Override
    public void delete(int p) {
        if (isPositionValid(p)) {
            shiftElementsBackward(p);
            elements[last - 1] = null;
            last--;
        }
    }

    // Метод возвращает следующую за p позицию в списке
    @Override
    public int next(int p) {
        if (isPositionValid(p)) {
            return p + 1;
        }
        throw new InvalidPositionException("Недопустимая позиция!");
    }

    // Метод возвращает предыдущую перед p позицию в списке
    @Override
    public int previous(int p) {
        if (isPositionValid(p)) {
            return p - 1;
        }
        throw new InvalidPositionException("Недопустимая позиция!");
    }

    // Метод делающий список пустым
    @Override
    public void makeNull() {
        for (int i = 0; i < last; i++) {
            elements[i] = null;
        }
        last = 0;
    }

    // Метод возвращает 1-ую позицию в списке
    @Override
    public int first() {
        return 1;
    }

    // Метод выводящий список на печать в порядке расположения элементов в списке
    @Override
    public void printList() {
        for (int i = 0; i < last; i++) {
            System.out.print(elements[i]+ "  ");
        }
        System.out.println();
    }

    // Вспомогательный метод для проверки допустимости позиции

    private boolean isPositionValid(int p) {
        return p >= first() && p <= end();
    }

    // Вспомогательный метод для смещения элементов вперед при вставке
    private void shiftElementsForward(int p) {
        for (int i = last; i >= p; i--) {
            elements[i] = elements[i - 1];
        }
    }

    // Вспомогательный метод для смещения элементов назад при удалении
    private void shiftElementsBackward(int p) {
        for (int i = p - 1; i < last - 1; i++) {
            elements[i] = elements[i + 1];
        }
    }
}
