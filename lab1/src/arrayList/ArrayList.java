package arrayList;

// Импортируем необходимые пакеты и классы
import exceptions.InvalidPositionException;
import interfaces.InterfaceList;
public class ArrayList<U extends Comparable<U>> implements InterfaceList<U> {
    private final U[] elements; // Массив, хранящий элементы списка
    private int size; // Текущий размер списка

    // Конструктор, инициализирующий список от массива
    public ArrayList(U[] elements) {
        this.elements = elements;  // Инициализируем массив элементами, переданными в конструктор.
        size = 0;  // Начальный размер списка - 0.
    }

    // Метод возвращающий позицию после последнего элемента
    @Override
    public int end() {
        return size + 1;  // Возвращаем позицию после последнего элемента.
    }

    // Метод вставляющий элемент x по позиции p
    @Override
    public void insert(U x, int p) {
        if (isPositionValid(p)) {  // Проверка на допустимость позиции.
            shiftElementsForward(p);  // Смещаем элементы вперед.
            elements[p - 1] = x;  // Вставляем элемент на указанную позицию.
            size++;  // Увеличиваем размер списка.
        }
    }

    // Метод возвращающий позицию элемента x в списке
    @Override
    public int locate(U x) {
        for (int i = 0; i < size; i++) {
            if (elements[i].compareTo(x) == 0) {
                return i + 1;  // Возвращаем позицию элемента x, если он найден.
            }
        }
        return end();  // Если элемент не найден, возвращаем позицию после последнего элемента.
    }

    // Метод возвращающий элемент из списка по позиции
    @Override
    public U retrieve(int p) {
        if (isPositionValid(p)) {
            return elements[p - 1];  // Возвращаем элемент на указанной позиции.
        }
        throw new InvalidPositionException("Недопустимая позиция!");  // Если позиция недопустима, выбрасываем исключение.
    }

    // Метод удаляющий элемент из списка по позиции
    @Override
    public void delete(int p) {
        if (isPositionValid(p)) {  // Проверка на допустимость позиции.
            shiftElementsBackward(p);  // Смещаем элементы назад.
            elements[size - 1] = null;  // Удаляем последний элемент.
            size--;  // Уменьшаем размер списка.
        }
    }

    // Метод возвращает следующую за p позицию в списке
    @Override
    public int next(int p) {
        if (isPositionValid(p)) {
            return p + 1;  // Возвращаем следующую позицию.
        }
        throw new InvalidPositionException("Недопустимая позиция!");  // Если позиция недопустима, выбрасываем исключение.
    }

    // Метод возвращает предыдущую перед p позицию в списке
    @Override
    public int previous(int p) {
        if (isPositionValid(p)) {
            return p - 1;  // Возвращаем предыдущую позицию.
        }
        throw new InvalidPositionException("Недопустимая позиция!");  // Если позиция недопустима, выбрасываем исключение.
    }

    // Метод делающий список пустым
    @Override
    public void makeNull() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;  // Устанавливаем все элементы в массиве в null.
        }
        size = 0;  // Устанавливаем размер списка в 0.
    }

    // Метод возвращает 1-ую позицию в списке
    @Override
    public int first() {
        return 1;  // Всегда возвращает 1.
    }

    // Метод выводящий список на печать
    @Override
    public void printList() {
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i]+ "  ");  // Выводим элементы списка.
        }
        System.out.println();
    }

    // Вспомогательный метод для проверки допустимости позиции
    private boolean isPositionValid(int p) {
        return p >= first() && p <= end();  // Проверка на допустимость позиции.
    }

    // Вспомогательный метод для смещения элементов вперед при вставке
    private void shiftElementsForward(int p) {
        for (int i = size; i >= p; i--) {
            elements[i] = elements[i - 1];  // Смещаем элементы вперед.
        }
    }

    // Вспомогательный метод для смещения элементов назад при удалении
    private void shiftElementsBackward(int p) {
        for (int i = p - 1; i < size - 1; i++) {
            elements[i] = elements[i + 1];  // Смещаем элементы назад.
        }
    }
}
