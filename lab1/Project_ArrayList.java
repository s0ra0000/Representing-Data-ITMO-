package arrayList;

import interfaces.InterfaceList;

@SuppressWarnings("ALL")
public class ArrayList<T extends Comparable<T>> implements InterfaceList<T> {
    private final T[] elements; // Массив, хранящий элементы списка
    private int last; // Последняя заполненная позиция

    // Конструктор, инициализирующий список от массива
    public ArrayList(T[] elements) {
        //Принимаем массив элементов как параметр.
        //Устанавливаем этот массив как основное хранилище элементов списка.
        //Инициализируем указатель последнего заполненного элемента (last) значением 0.
    }

    // Метод возвращающий позицию после последнего элемента
    @Override
    public int end() {
        //Возвращаем значение last + 1.
    }


    // Метод вставляющий элемент x по позиции p
    @Override
    public void insert(T x, int p) {
        //Проверяем, допустима ли позиция p.
        //Сдвигаем все элементы, начиная с позиции p, на одну позицию вперед с помощью метода shiftElementsForward().
        //Помещаем элемент x на позицию p - 1.
        //Увеличиваем last на 1.
    }

    // Метод возвращающий позицию элемента x в списке
    @Override
    public int locate(T x) {
        //Начинаем с начала списка.
        //Проходим по каждому элементу, пока не найдем элемент или пока не достигнем конца списка.
        //Если элемент найден, возвращаем его позицию.
        //Если элемент не найден, возвращаем позицию после последнего элемента, то есть end().
    }

    // Метод возвращающий элемент из списка по позиции
    @Override
    public T retrieve(int p) {
        //Проверяем допустимость позиции p.
        //Возвращаем элемент на позиции p - 1.
        //Если позиция недопустима, выбрасываем исключение.
    }

    // Метод удаляющий элемент из списка по позиции
    @Override
    public void delete(int p) {
        //Проверяем допустимость позиции p.
        //Сдвигаем все элементы после p на одну позицию назад с помощью метода shiftElementsBackward().
        //Обнуляем последний элемент.
        //Уменьшаем last на 1.
    }

    // Метод возвращает следующую за p позицию в списке
    @Override
    public int next(int p) {
        //Проверяем допустимость позиции p.
        //Возвращаем p + 1.
        //Если позиция недопустима, выбрасываем исключение.
    }

    // Метод возвращает предыдущую перед p позицию в списке
    @Override
    public int previous(int p) {
        //Проверяем допустимость позиции p.
        //Возвращаем p - 1.
        //Если позиция недопустима, выбрасываем исключение.
    }

    // Метод делающий список пустым
    @Override
    public void makeNull() {
        //Проходим по всему списку, обнуляя каждый элемент.
        //Устанавливаем last в 0.
    }

    // Метод возвращает 1-ую позицию в списке
    @Override
    public int first() {
        //Возвращаем 1.
    }

    // Метод выводящий список на печать в порядке расположения элементов в списке
    @Override
    public void printList() {
        //Начинаем с начала списка.
        //Проходим по каждому элементу, выводя его значение.
        //В конце выводим строку null для наглядности.
    }

    // Вспомогательный метод для проверки допустимости позиции

    private boolean isPositionValid(int p) {
        //Проверяем, что p больше или равно 1 и меньше или равно позиции после последнего элемента.
    }

    // Вспомогательный метод для смещения элементов вперед при вставке
    private void shiftElementsForward(int p) {
        //Начинаем с конца списка и двигаемся к началу до позиции p, сдвигая каждый элемент на одну позицию вперед.
    }

    // Вспомогательный метод для смещения элементов назад при удалении
    private void shiftElementsBackward(int p) {
        //Начинаем с позиции p и двигаемся к концу списка, сдвигая каждый элемент на одну позицию назад.
    }
}
