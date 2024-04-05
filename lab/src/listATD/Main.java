package listATD;



import utils.Data;



//import listATD.arrayList.*;
//import listATD.linkedList.*;
//import listATD.doublyLinkedList.*;
import listATD.cursorList.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
         List myList = new List();

        Data data1 = new Data("Address1", "Element1");

        myList.insert(data1, myList.first());
        myList.insert(data1, myList.end());
        myList.insert(data1, myList.end());
        myList.insert(data1, myList.end());
        myList.insert(data1, myList.end());



        System.out.println("Список перед удалением:");
        myList.printlist();

        System.out.println("\n--- Начало удаления ---");

        removeDuplicates(myList);

        System.out.println("Список после удаления:");
        myList.printlist();
    }
    private static void removeDuplicates(IList list) {

        Position outer = list.first(); // Получение первой позиции в списке и ее сохранение в переменной 'outer'
        while (!outer.equals(list.end())) { // Перебор всех элементов списка до тех пор, пока 'outer' не достигнет конца списка
            Data outerData = list.retrieve(outer); // Получение данных, хранящихся на позиции 'outer'

            Position inner = list.next(outer); // Получение следующей позиции после 'outer' и ее сохранение в переменной 'inner'

            while (!inner.equals(list.end())) {// Внутренний цикл для сравнения элемента 'outer' с каждым последующим элементом списка
                Data innerData = list.retrieve(inner); // Получение данных, хранящихся на позиции 'inner'
                if (outerData.equals(innerData)){  // Сравнение данных 'outerData' и 'innerData'
//                    System.out.println(Arrays.toString(innerData.getName()) + " deleted");
                    list.delete(inner);  // Если данные совпадают, удаляем элемент на позиции 'inner'
                } else {
                    inner = list.next(inner);  // Если данные не совпадают, переходим к следующему элементу списка
                }
//                System.out.println(Arrays.toString(innerData.getName()) + " -> " + inner.index);
            }
            outer = list.next(outer); // Переход к следующему элементу во внешнем цикле
        }
    }

}