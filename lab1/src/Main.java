import arrayList.ArrayList;
import linkedList.LinkedList;
import utils.DuplicateRemover;

public class Main {
    public static void main(String[] args) {


        ArrayList<String> stringList = new ArrayList<>(new String[10]);
        ArrayList<Integer> integerList = new ArrayList<>(new Integer[10]);

//        LinkedList<String> stringList = new LinkedList<>();
//        LinkedList<Integer> integerList = new LinkedList<>();

        System.out.println("-------- ТЕСТ #1 ---------");
        integerList.insert(5, integerList.first());         // Вставка 5 на позицию 1: [5]
        integerList.insert(10, integerList.end());          // Вставка 10 на позицию 2: [5, 10]
        integerList.insert(3, integerList.previous(2));  // Вставка 3 на позицию 1: [3, 5, 10]
        integerList.insert(12, integerList.next(3));     // Вставка 12 на позицию 4: [3, 5, 10,12]
        integerList.insert(15, 5);     // Вставка 15 на позицию 5: [3, 5, 10, 12, 15]
        System.out.println("Список:");
        integerList.printList();

        int posElemInt = 5;
        int posInt = integerList.locate(posElemInt);  // pos должен быть равен 2
        System.out.println("Позиция элемента {"+ posElemInt +"} : "+posInt);

        int retElemInt = 2;
        int elementInt = integerList.retrieve(retElemInt);  // element должен быть равен 5
        System.out.println("Элемент на позиции {"+retElemInt + "} : "+elementInt);

        int delElemInt = 2;
        integerList.delete(delElemInt);  // Удаление элемента на позиции 2: [3, 10]
        System.out.println("После удаление элемент  позиции {" +delElemInt + "}:");
        integerList.printList();

        integerList.makeNull();
        System.out.println("Тест для очистки списка:");
        integerList.printList();

        System.out.println("Тест для удаления дубликатов");
        integerList.insert(1, 1);
        integerList.insert(2, 2);
        integerList.insert(3, 3);
        integerList.insert(3, 4);
        integerList.insert(3, 5);
        integerList.insert(4, 6);
        integerList.insert(5, 7);
        integerList.insert(5, 8);
        integerList.printList();
        DuplicateRemover.removeDuplicates(integerList);
        integerList.printList();
        System.out.println();

        System.out.println("-------- ТЕСТ #2 ---------");
        stringList.insert("Apple", stringList.first());         // Вставка 5 на позицию 1: [5]
        stringList.insert("Banana", stringList.end());          // Вставка 10 на позицию 2: [5, 10]
        stringList.insert("Cherry", stringList.previous(2));  // Вставка 3 на позицию 1: [3, 5, 10]
        stringList.insert("Durian", stringList.next(3));     // Вставка 12 на позицию 4: [3, 5, 10,12]
        stringList.insert("Eggplant", 5);     // Вставка 15 на позицию 5: [3, 5, 10, 12, 15]
        System.out.println("Список:");
        stringList.printList();

        String posElemStr = "Cherry";
        int posStr = stringList.locate(posElemStr);  // pos должен быть равен 2
        System.out.println("Позиция элемента {"+ posElemStr +"} : "+posStr);

        int retElemStr = 1;
        String elementStr = stringList.retrieve(retElemStr);  // element должен быть равен 5
        System.out.println("Элемент на позиции {"+retElemStr + "} : "+elementStr);

        int delElemStr = 4;
        stringList.delete(delElemInt);  // Удаление элемента на позиции 2: [3, 10]
        System.out.println("После удаление элемент  позиции {" +delElemInt + "}:");
        stringList.printList();

        stringList.makeNull();
        System.out.println("Тест для очистки списка:");
        stringList.printList();

        System.out.println("Тест для удаления дубликатов");
        stringList.insert("Apple", 1);
        stringList.insert("Banana", 2);
        stringList.insert("Cherry", 3);
        stringList.insert("Cherry", 4);
        stringList.insert("Cherry", 5);
        stringList.insert("Durian", 6);
        stringList.insert("Eggplant", 7);
        stringList.insert("Eggplant", 8);
        stringList.printList();
        DuplicateRemover.removeDuplicates(stringList);
        stringList.printList();
        System.out.println();
    }
}