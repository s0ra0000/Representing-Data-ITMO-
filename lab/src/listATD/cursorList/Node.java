package listATD.cursorList;

import utils.Data;

public class Node {
    Data data;
    int next;

    Node(Data data){
        this.data = data;
        next = -1;
    }
    Node(int next){
        this.next = next;
        this.data = null;
    }
}
