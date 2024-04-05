package stackATD;


import utils.Data;

//import stackATD.array.*;
//import stackATD.linkedList.*;
import stackATD.list.*;
public class Main {
    public static void main(String[] args){
        Stack stack = new Stack();
        stack.push(new Data("address1", "name1"));
        stack.push(new Data("address2", "name2"));
        System.out.println("Pop element: " + stack.pop());
        System.out.println("Pop element: " + stack.pop());
        System.out.println("Is stack empty?: " + stack.isEmpty());
    }
}
