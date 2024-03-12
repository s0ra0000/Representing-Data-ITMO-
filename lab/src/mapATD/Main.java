package mapATD;

//import mapATD.linkedList.*;
import mapATD.list.*;

public class Main {
    public static void main(String[] args){
        System.out.println("Testing LinkedListMap");
        Map map = new Map();
        map.assign("123 Street".toCharArray(), "Alice".toCharArray());
        map.assign("456 Avenue".toCharArray(), "Bob".toCharArray());
        map.print();
    }
}
