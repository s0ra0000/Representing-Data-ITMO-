package mapATD.list;

import exceptions.InvalidPositionException;
import listATD.arrayList.List;
import listATD.arrayList.Position;
import utils.Data;

public class Map {
    private List mapList;

    public Map() {
        this.mapList = new List();
    }

    public void makenull() {
        this.mapList.makenull();
    }

    // Метод assign принимает адрес и имя в виде массивов символов
    public void assign(char[] address, char[] name) {
        Data newItem = new Data(address, name);
        boolean found = false;
        for (Position p = this.mapList.first(); !p.equals(this.mapList.end()); p = this.mapList.next(p)) {
            try {
                Data currentItem = this.mapList.retrieve(p);
                if (java.util.Arrays.equals(currentItem.getAddress(), address)) {
                    // Если нашли совпадение, заменяем элемент на новый
                    this.mapList.delete(p); // Сначала удаляем старый
                    this.mapList.insert(newItem, p); // Вставляем обновленный на ту же позицию
                    found = true;
                    break;
                }
            } catch (InvalidPositionException e) {
                System.err.println("Invalid position encountered: " + e.getMessage());
                // Обработка ошибки
            }
        }
        if (!found) {
            // Если совпадение не найдено, добавляем новый элемент в конец списка
            this.mapList.insert(newItem, this.mapList.end());
        }
    }

    // Метод compute возвращает true, если находит соответствие адреса, иначе false
    public boolean compute(char[] address, char[] r) {
        for (Position p = mapList.first(); !p.equals(mapList.end()); p = mapList.next(p)) {
            try {
                Data currentItem = mapList.retrieve(p);
                if (new String(currentItem.getAddress()).trim().equals(new String(address).trim())) {
                    System.arraycopy(currentItem.getName(), 0, r, 0, currentItem.getName().length);
                    return true;
                }
            } catch (InvalidPositionException e) {
                System.err.println("Invalid position encountered: " + e.getMessage());
                return false;
            }
        }
        return false;
    }
    public void print() {
        for (Position p = mapList.first(); !p.equals(mapList.end()); p = mapList.next(p)) {
            try {
                Data currentItem = mapList.retrieve(p);
                System.out.println(new String(currentItem.getAddress()) + " -> " + new String(currentItem.getName()));
            } catch (InvalidPositionException e) {
                System.err.println("Error retrieving data: " + e.getMessage());
            }
        }
    }


}
