package com.shanhai.dataStructe._13avltree.test;

import com.shanhai.dataStructe._13avltree.AVLTree;
import org.junit.jupiter.api.Test;

public class AVLTreeTest {
    @Test
    public void testRemoveWithoutBalance() {
        AVLTree<Integer, String> tree = new AVLTree<>();
        tree.put(1, "A");
        tree.put(2, "B");
        tree.put(3, "C");
        tree.put(4, "D");
        tree.put(5, "E");
        tree.put(6, "F");
        tree.put(7, "G");
        tree.put(8, "H");
        tree.put(9, "I");
        tree.put(10, "I");
        tree.put(11, "J");
        tree.put(12, "J");
        tree.put(13, "J");
        tree.put(14, "J");
        tree.put(15, "J");
        tree.put(16, "J");
        tree.remove(5);
        tree.remove(1);
        tree.remove(4);
        tree.remove(16);
        tree.remove(7);
        tree.remove(13);
        tree.remove(14);
        tree.remove(15);
        //assertEquals("F", tree.root.right.left.value);
        System.out.println();
    }
}
