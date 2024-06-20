package com.shanhai.dataStructe._12binarySearchTree.test;

import com.shanhai.dataStructe._12binarySearchTree.BinarySearchTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestBinarySearchTree {
    public BinarySearchTree<Integer, String> createTree() {
        /*
                     4
                   /   \
                  2     6
                 / \   / \
                1   3 5   7
         */
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        tree.put(4, "小昭");
        tree.put(2, "周芷若");
        tree.put(6, "赵敏");
        tree.put(1, "张无忌");
        tree.put(3, "宋青书");
        tree.put(7, "殷离");
        tree.put(5, "说不得");
        return tree;
    }

    @Test
    void get() {
        BinarySearchTree<Integer, String> tree = createTree();
        assertEquals("张无忌", tree.get(1));
        assertEquals("周芷若", tree.get(2));
        assertEquals("宋青书", tree.get(3));
        assertEquals("小昭", tree.get(4));
        assertEquals("说不得", tree.get(5));
        assertEquals("赵敏", tree.get(6));
        assertEquals("殷离", tree.get(7));
        assertNull(tree.get(8));
    }

    @Test
    public void minMax() {
        BinarySearchTree<Integer, String> tree = createTree();
        assertEquals("张无忌", tree.min());
        assertEquals("殷离", tree.max());
    }

    @Test
    public void put() {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        tree.put(4, "4");
        tree.put(2, "2");
        tree.put(6, "6");
        tree.put(1, "1");
        tree.put(3, "3");
        tree.put(7, "7");
        tree.put(5, "5");
        assertTrue(tree.isSameTree(createTree()));
        tree.put(1, "教主张无忌");
        assertEquals("教主张无忌", tree.get(1));
    }

    @Test
    public void predecessor() {
        /*
                     4
                   /   \
                  2     7
                 / \   / \
                1   3 6   8
                     /
                    5
         */
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        tree.put(4, 4);
        tree.put(2, 2);
        tree.put(7, 7);
        tree.put(1, 1);
        tree.put(3, 3);
        tree.put(6, 6);
        tree.put(8, 8);
        tree.put(5, 5);

        assertNull(tree.predecessor(1));
        assertEquals(1, tree.predecessor(2));
        assertEquals(2, tree.predecessor(3));
        assertEquals(3, tree.predecessor(4));
        assertEquals(4, tree.predecessor(5));
        assertEquals(5, tree.predecessor(6));
        assertEquals(6, tree.predecessor(7));
        assertEquals(7, tree.predecessor(8));
    }

    @Test
    public void successor() {
        /*
                     5
                   /   \
                  2     7
                 / \   / \
                1   3 6   8
                     \
                      4
         */
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        tree.put(5, 5);
        tree.put(2, 2);
        tree.put(7, 7);
        tree.put(1, 1);
        tree.put(3, 3);
        tree.put(6, 6);
        tree.put(8, 8);
        tree.put(4, 4);

        assertEquals(2, tree.successor(1));
        assertEquals(3, tree.successor(2));
        assertEquals(4, tree.successor(3));
        assertEquals(5, tree.successor(4));
        assertEquals(6, tree.successor(5));
        assertEquals(7, tree.successor(6));
        assertEquals(8, tree.successor(7));
        assertNull(tree.successor(8));
    }

    @Test
    @DisplayName("删除叶子节点")
    public void delete1() {
        /*
                     4
                   /   \
                  2     7
                 / \   / \
                1   3 6   8
                     /
                    5
         */

        BinarySearchTree<Integer, Integer> tree1 = new BinarySearchTree<>();
        tree1.put(4, 4);
        tree1.put(2, 2);
        tree1.put(7, 7);
        tree1.put(1, 1);
        tree1.put(3, 3);
        tree1.put(6, 6);
        tree1.put(8, 8);
        tree1.put(5, 5);

        assertEquals(1, tree1.remove(1));
        assertEquals(3, tree1.remove(3));
        assertEquals(5, tree1.remove(5));
        assertEquals(8, tree1.remove(8));


        /*
                     4
                   /   \
                  2     7
                       /
                      6
         */
        BinarySearchTree<Integer, Integer> tree2 = new BinarySearchTree<>();
        tree2.put(4, 4);
        tree2.put(2, 2);
        tree2.put(7, 7);
        tree2.put(6, 6);

        assertTrue(tree1.isSameTree(tree2));
    }

    @Test
    @DisplayName("删除只有一个孩子节点")
    public void delete2() {
        /*
                     4
                   /   \
                  2     7
                 / \   /
                1   3 6
                     /
                    5
         */
        BinarySearchTree<Integer, Integer> tree1 = new BinarySearchTree<>();
        tree1.put(4, 4);
        tree1.put(2, 2);
        tree1.put(7, 7);
        tree1.put(1, 1);
        tree1.put(3, 3);
        tree1.put(6, 6);
        tree1.put(5, 5);

        assertEquals(7, tree1.remove(7));


        /*
                     4
                   /   \
                  2     6
                 / \   /
                1   3 5
         */
        BinarySearchTree tree2 = new BinarySearchTree();
        tree2.put(4, 4);
        tree2.put(2, 2);
        tree2.put(6, 6);
        tree2.put(1, 1);
        tree2.put(3, 3);
        tree2.put(5, 5);

        assertTrue(tree1.isSameTree(tree2));
    }

    @Test
    @DisplayName("删除只有一个孩子节点")
    public void delete0() {
        /*
                     4
         */
        BinarySearchTree<Integer, Integer> tree1 = new BinarySearchTree<>();
        tree1.put(4, 4);

        assertEquals(4, tree1.remove(4));

        BinarySearchTree tree2 = new BinarySearchTree();

        assertTrue(tree1.isSameTree(tree2));
    }

    @Test
    @DisplayName("删除有两个孩子节点, 被删除与后继不邻")
    public void delete3() {
        /*
                      4
                   /     \
                  2      7
                 / \   /   \
                1   3 5     8
                       \
                        6
         */
        BinarySearchTree<Integer, Integer> tree1 = new BinarySearchTree<>();
        tree1.put(4, 4);
        tree1.put(2, 2);
        tree1.put(7, 7);
        tree1.put(1, 1);
        tree1.put(3, 3);
        tree1.put(5, 5);
        tree1.put(8, 8);
        tree1.put(6, 6);

        assertEquals(4, tree1.remove(4));


        /*
                      5
                   /     \
                  2      7
                 / \   /   \
                1   3 6     8

         */
        BinarySearchTree<Integer, Integer> tree2 = new BinarySearchTree<>();
        tree2.put(5, 5);
        tree2.put(2, 2);
        tree2.put(7, 7);
        tree2.put(1, 1);
        tree2.put(3, 3);
        tree2.put(6, 6);
        tree2.put(8, 8);

        assertTrue(tree1.isSameTree(tree2));
    }

    @Test
    @DisplayName("删除有两个孩子节点, 被删除与后继相邻")
    public void delete4() {
        /*
                     4
                   /   \
                  2     5
                 / \     \
                1   3     6

         */
        BinarySearchTree<Integer, Integer> tree1 = new BinarySearchTree<>();
        tree1.put(4, 4);
        tree1.put(2, 2);
        tree1.put(5, 5);
        tree1.put(1, 1);
        tree1.put(3, 3);
        tree1.put(6, 6);
        assertEquals(4, tree1.remove(4));


        /*
                     5
                   /  \
                  2    6
                 / \
                1   3

         */
        BinarySearchTree<Integer, Integer> tree2 = new BinarySearchTree<>();
        tree2.put(5, 5);
        tree2.put(2, 2);
        tree2.put(6, 6);
        tree2.put(1, 1);
        tree2.put(3, 3);

        assertTrue(tree1.isSameTree(tree2));
    }

    @Test
    public void less() {

           /*    4
               /   \
              2     6
             / \   / \
            1   3 5   7
            */

        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();
        tree.put(4, 4);
        tree.put(2, 2);
        tree.put(6, 6);
        tree.put(1, 1);
        tree.put(3, 3);
        tree.put(5, 5);
        tree.put(7, 7);

        assertIterableEquals(List.of(1, 2, 3, 4, 5), tree.less(6));
        assertIterableEquals(List.of(7), tree.greater(6));
        assertIterableEquals(List.of(3, 4, 5), tree.between(3, 5));
//          tree.greater(6);
    }


    public BinarySearchTree<String, String> createTree1() {
        /*
                     4
                   /   \
                  2     6
                 / \   / \
                1   3 5   7
         */

        BinarySearchTree<String, String> tree = new BinarySearchTree<>();
        tree.put("d", "小昭");
        tree.put("b", "周芷若");
        tree.put("f", "赵敏");
        tree.put("a", "张无忌");
        tree.put("c", "宋青书");
        tree.put("e", "说不得");
        tree.put("g", "殷离");
        return tree;
    }

    @Test
    public void get11() {
        BinarySearchTree<String, String> tree = createTree1();
        assertEquals("张无忌", tree.get("a"));
        assertEquals("周芷若", tree.get("b"));
        assertEquals("宋青书", tree.get("c"));
        assertEquals("小昭", tree.get("d"));
        assertEquals("说不得", tree.get("e"));
        assertEquals("赵敏", tree.get("f"));
        assertEquals("殷离", tree.get("g"));
        assertNull(tree.get("h"));
    }
}