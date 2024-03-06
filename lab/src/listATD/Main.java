package listATD;



import utils.Data;



//import listATD.arrayList.*;
//import listATD.linkedList.*;
//import listATD.doublyLinkedList.*;
import listATD.cursorList.*;

public class Main {
    public static void main(String[] args) {
         List myList = new List();

        Data data1 = new Data("Address1", "Element1");
        Data data2 = new Data("Address2", "Element2");
        Data data3 = new Data("Address3", "Element3");
        Data data4 = new Data("Address4", "Element4");
        Data duplicateData = new Data("Address2", "Element2");

        myList.insert(data1, myList.first());
        myList.insert(data2, myList.end());
        myList.insert(data3, myList.end());
        myList.insert(data3, myList.end());
        myList.insert(data3, myList.end());



        myList.insert(duplicateData, myList.end());
        myList.insert(duplicateData, myList.end());
        myList.insert(duplicateData, myList.end());
        myList.insert(duplicateData, myList.end());
        myList.insert(duplicateData, myList.end());
        myList.insert(data3, myList.end());
        myList.insert(data4, myList.end());

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
                    list.delete(inner);  // Если данные совпадают, удаляем элемент на позиции 'inner'
                } else {
                    inner = list.next(inner);  // Если данные не совпадают, переходим к следующему элементу списка
                }
            }
            outer = list.next(outer); // Переход к следующему элементу во внешнем цикле
        }
    }

}