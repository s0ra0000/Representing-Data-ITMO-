package queueATD.list;

import listATD.arrayList.List;
import utils.Data;

public class Queue {
    private final List list;

    public Queue() {
        list = new List();
    }

    public void enqueue(Data data) {
        list.insert(data, list.end());
    }

    public Data dequeue() {
        Data data = list.retrieve(list.first());
        list.delete(list.first());
        return data;
    }

    public Data front() {
        return list.retrieve(list.first());
    }

    public boolean isEmpty() {
        return list.first().equals(list.end());
    }

    public void makenull() {
        list.makenull();
    }
}
