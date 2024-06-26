package mapATD.list;

import listATD.arrayList.List;
import listATD.arrayList.Position;
import utils.Data;

public class Map {
    private final List list; // Объявление переменной для хранения списка

    public Map() {
        this.list = new List(); // Конструктор создает новый список
    }

    public void makenull() {
        this.list.makenull(); // Метод очищает список
    }

    // Метод assign принимает адрес и имя в виде массивов символов
    public void assign(char[] name, char[] address) {
        if(list.first().equals(list.end())){
            list.insert(new Data(address, name),list.first()); // Если список пуст, вставляет данные в начало
            return;
        }
        Position p = findByName(name); // Находит позицию по имени
        if(p.equals(list.end())){
            list.insert(new Data(address, name),list.first()); // Если имя не найдено, вставляет данные в начало списка

        }else{
            list.retrieve(p).setAddress(address); // Если имя найдено, обновляет адрес у найденных данных

        }
    }

    // Метод compute возвращает true, если находит соответствие адреса, иначе false
    public boolean compute(char[] address, char[] name) {
        Position p = findByName(name); // Находит позицию по имени
        if(!p.equals(list.end())) {
            list.retrieve(p).setAddress(address); // Если имя найдено, обновляет адрес и возвращает true
            return true;
        }
        return false; // Возвращает false, если имя не найдено
    }

    public Position findByName(char[] name){
        Position p = list.first(); // Начинает поиск с первого элемента списка
        while (!p.equals(list.end())) {
            Data currentItem = list.retrieve(p); // Получает текущие данные для сравнения
            if (compareCharArrays(currentItem.getName(), name)) {
                return p; // Возвращает позицию, если имя найдено
            }
            p = list.next(p); // Переход к следующему элементу списка
        }
        return p; // Возвращает конец списка, если имя не найдено
    }
    public void print() {
        list.printlist(); // Печатает все элементы списка
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
