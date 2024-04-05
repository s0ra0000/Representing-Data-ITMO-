package treeATD;

import utils.Label;
//import treeATD.leftSonsRightSiblings.*;
import treeATD.sonsLists.*;
public class Main {
    public static void main(String[] args){
        Tree t1 = new Tree();
        t1.create(new Label('F'));
        t1.create(new Label('B'));
        Tree t2 = new Tree();
        t2.create(new Label('D'));
        Tree t3 = new Tree();
        t3.create(new Label('E'));
        t2.create(new Label('C'),t3);
        t1.create(new Label('A'),t2);
        Tree.print();
        System.out.println("Inorder:");
        inorder(t1,t1.root());
    }
    public static void inorder(Tree tree, int nodeIndex) {
        if (nodeIndex == -1) return; // Базовый случай: если индекс узла EMPTY, заканчиваем обход.

        int leftMostChild = tree.leftMostChild(nodeIndex); // Получение самого левого потомка текущего узла.
        inorder(tree, leftMostChild); // Рекурсивный симметричный обход левого поддерева.

        System.out.print(tree.label(nodeIndex).getValue() + ","); // Посещение текущего узла.

        int rightSiblingOfLeftMostChild = tree.rightSibling(leftMostChild); // Получение правого сиблинга самого левого потомка.
        while (rightSiblingOfLeftMostChild != -1) { // Проход по сиблингам самого левого потомка.
            inorder(tree, rightSiblingOfLeftMostChild); // Каждый сиблинг является корнем поддерева, выполним симметричный обход.
            rightSiblingOfLeftMostChild = tree.rightSibling(rightSiblingOfLeftMostChild); // Переход к следующему сиблингу.
        }
    }
}
