package queueATD;

import utils.Data;

//import queueATD.array.*;
import queueATD.list.*;
//import queueATD.ringLinkedList.*;

public class Main {
    public static void main(String[] args){
        Data data1 = new Data("Address1", "Name1");
        Data data2 = new Data("Address2", "Name2");
        Data data3 = new Data("Address3", "Name3");
        Queue queue = new Queue();
        queue.enqueue(data1);
        queue.enqueue(data2);
        queue.enqueue(data3);

        System.out.println("ArrayQueue Dequeue: " + queue.dequeue().toString());
        System.out.println("ArrayQueue Dequeue: " + queue.dequeue().toString());
        System.out.println("ArrayQueue Dequeue: " + queue.dequeue().toString());


    }
}
