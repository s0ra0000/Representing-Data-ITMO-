package treeATD;

import utils.Label;
import treeATD.leftSonsRightSiblings.*;
//import treeATD.sonsLists.*;
public class Main {
    public static void main(String[] args){
        Tree t1 = new Tree();
        t1.create(new Label('a'));
        t1.create(new Label('b'));
        Tree t2 = new Tree();
        t2.create(new Label('d'));
        t2.create(new Label('e'));
        t1.create(new Label('f'), t2);
//        Tree.print();
//        t1.inorderTraversal(t1.root());
        inorder(t1,t1.root());
    }

    public static void inorder(Tree tree, int index) {
        // Print the current node's label first
        System.out.print(tree.label(index).value + " ");

        // Then visit the leftmost child of the current node
        int child = tree.leftMostChild(index);
        if (child != -1) { // Check if the node has at least one child
            inorder(tree, child); // Recursively call inorder on the child

            // Traverse through all right siblings of the leftmost child
            int sibling = tree.rightSibling(child);
            while (sibling != -1) {
                inorder(tree, sibling); // Recursively call inorder on each sibling
                sibling = tree.rightSibling(sibling); // Move to the next sibling
            }
        }
    }


}
