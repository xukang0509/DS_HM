package com.shanhai.dataStructe._14rbtree.test;

import com.shanhai.dataStructe._14rbtree.RedBlackTree;
import org.junit.jupiter.api.Test;

public class RBTreeTest {
    @Test
    public void testPut() {
        RedBlackTree<Integer, Integer> rbTree = new RedBlackTree<>();
        int[] arr = new int[]{10, 35, 47, 11, 5, 57, 39, 14, 27, 26, 84, 75, 63, 41, 37, 24, 96};
        for (int i : arr) {
            rbTree.put(i, i);
        }
        System.out.println();

        rbTree.remove(41);
        rbTree.remove(5);


        System.out.println();
    }
}
