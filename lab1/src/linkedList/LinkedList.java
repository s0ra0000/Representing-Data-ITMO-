package linkedList;

import exceptions.InvalidPositionException;
import interfaces.InterfaceList;

public class LinkedList<U extends Comparable<U>> implements InterfaceList<U> {
    private class Node {
        U data;
        Node next;

        Node(U data) {
            this.data = data;
            this.next = null;
        }
    }
    private Node head;
    private int size = 0;
    //Конструктор, инициализирующий связный список
    public LinkedList() {
        this.head = null;
    }
    // Метод возвращающий позицию после последнего элемента
    @Override
    public int end() {
        return size+1;
    }
    // Метод вставляющий элемент x по позиции p
    @Override
    public void insert(U x, int p) {
        if (isPositionValid(p)) {
            Node newNode = new Node(x);

            if (head == null) {
                head = newNode;
            } else if (p == 1) {
                newNode.next = head;
                head = newNode;
            } else {
                Node current = head;
                for (int i = 1; i < p - 1; i++) {
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            }
            size++;
        }

    }
    // Метод возвращающий позицию элемента x в списке
    @Override
    public int locate(U x) {
        Node current = head;
        int index = 1;
        while (current != null) {
            if (current.data.equals(x)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return end();
    }
    // Метод возвращающий элемент из списка по позиции
    @Override
    public U retrieve(int p) {
        if(isPositionValid(p)){
            Node current = head;
            for (int i = 1; i < p; i++) {
                current = current.next;
            }
            return current.data;
        }
        else{
            throw new InvalidPositionException("Invalid position");
        }
    }
    // Метод удаляющий элемент из списка по позиции
    @Override
    public void delete(int p) {
        if(isPositionValid(p)){
            if (p == 1) {
                head = head.next;
            } else {
                Node current = head;
                for (int i = 1; i < p - 1; i++) {
                    current = current.next;
                }
                current.next = current.next.next;
            }
            size--;
        }
        else{
            throw new InvalidPositionException("Invalid position");
        }

    }
    // Метод возвращает следующую за p позицию в списке
    @Override
    public int next(int p) {
        if (isPositionValid(p))
            return p + 1;
        else
            throw new InvalidPositionException("Invalid position");
    }
    // Метод возвращает предыдущую перед p позицию в списке
    @Override
    public int previous(int p) {
        if(isPositionValid(p))
           return p - 1;
        else
            throw new InvalidPositionException("Invalid position");
    }
    // Метод делающий список пустым
    @Override
    public void makeNull() {
        head = null;
        size = 0;
    }
    // Метод возвращает 1-ую позицию в списке
    @Override
    public int first() {
        return 1;
    }
    // Метод выводящий список на печать в порядке расположения элементов в списке
    @Override
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    // Вспомогательный метод для проверки допустимости позиции
    private boolean isPositionValid(int p) {
        return p >= first() && p <= end();
    }

}
