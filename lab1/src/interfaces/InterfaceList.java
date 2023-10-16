package interfaces;

public interface InterfaceList<U extends Comparable<U>> {
    int end();
    void insert(U x, int p);
    int locate(U x);
    U retrieve(int p);
    void delete(int p);
    int next(int p);
    int previous(int p);
    void makeNull();
    int first();
    void printList();
}
