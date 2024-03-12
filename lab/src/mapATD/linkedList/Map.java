package mapATD.linkedList;

import utils.Data;

public class Map {
    public static class Node {
        Data data;
        Node next;

        public Node(Data data) {
            this.data = data;
            this.next = null;
        }
    }
    private Node head;

    public Map() {
        this.head = null;
    }

    public void makenull() {
        head = null;
    }

    public void assign(char[] address, char[] name) {
        Node current = head;
        Node previous = null;
        while (current != null) {
            if (new String(current.data.getAddress()).equals(new String(address))) {
                current.data.setName(name);
                return;
            }
            previous = current;
            current = current.next;
        }
        Data newData = new Data(address, name);
        Node newNode = new Node(newData);
        if (previous == null) { // Список был пуст
            head = newNode;
        } else {
            previous.next = newNode;
        }
    }

    public boolean compute(char[] address, char[] r) {
        Node current = head;
        while (current != null) {
            if (new String(current.data.getAddress()).equals(new String(address))) {
                System.arraycopy(current.data.getName(), 0, r, 0, current.data.getName().length);
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(new String(current.data.getAddress()) + " -> " + new String(current.data.getName()));
            current = current.next;
        }
    }

}
