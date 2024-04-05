package mapATD;

import mapATD.linkedList.*;
//import mapATD.list.*;

public class Main {
    public static void main(String[] args){
        Map map = new Map();

        map.assign("Name1".toCharArray(), "Address1".toCharArray());
        map.assign("Name2".toCharArray(), "Address2".toCharArray());

        map.assign("Name2".toCharArray(), "Address8".toCharArray());
        System.out.println(map.compute("Address9".toCharArray(),"Name1".toCharArray()));

        map.print();
    }
}
