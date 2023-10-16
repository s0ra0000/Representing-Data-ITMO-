package utils;

import arrayList.ArrayList;
import interfaces.InterfaceList;

// Класс с обобщенным статическим методом удаления дубликатов из списка
public class DuplicateRemover {
    // Обобщенный статический метод для удаления дубликатов
    // Принимает только списки, реализующие интерфейс Comparable<T>
    public static <T extends Comparable<T>> void removeDuplicates(InterfaceList<T> list) {
        int size = list.end();
        for (int i = 1; i < size; i++) {
            T element = list.retrieve(i); // Получить элемент по позиции
            int j = i + 1;
            while (j < size) { // Смотрим только назад
                if (list.retrieve(j).compareTo(element) == 0) { // Если элементы равны
                    list.delete(j); // Удаляем дубликат
                    size--;
                } else {
                    j++;
                }
            }
        }
    }
}
