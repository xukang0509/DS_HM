package com.shanhai.dataStructe._11binaryTree;

public class TreeTraversalMorris {
    public static void main(String[] args) {
         /*
                 1
               /   \
              2     3
             / \   /  \
            4   7 5    6
         */
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, new TreeNode(7)),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );

        postOrderMorris(root);
        System.out.println(); // 4 7 2 5 6 3 1

        preOrderMorris(root);
        System.out.println(); // 1 2 4 7 3 5 6

        inOrderMorris(root);
        System.out.println(); // 4 2 7 1 5 3 6


    }

    /**
     * 前序遍历-Morris解法
     *
     * @param node 节点
     */
    private static void preOrderMorris(TreeNode node) {
        TreeNode cur = node, leftRight = null;
        while (cur != null) {
            if (cur.left != null) {
                leftRight = cur.left;
                while (leftRight.right != null && leftRight.right != cur) {
                    leftRight = leftRight.right;
                }
                if (leftRight.right == null) {
                    System.out.print(cur.val + "\t");
                    leftRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    leftRight.right = null;
                }
            } else {
                System.out.print(cur.val + "\t");
            }
            cur = cur.right;
        }
    }

    /**
     * 中序遍历-Morris解法
     *
     * @param node 节点
     */
    private static void inOrderMorris(TreeNode node) {
        TreeNode cur = node, leftRight = null;
        while (cur != null) {
            if (cur.left != null) {
                leftRight = cur.left;
                while (leftRight.right != null && leftRight.right != cur) {
                    leftRight = leftRight.right;
                }
                if (leftRight.right == null) {
                    leftRight.right = cur;
                    cur = cur.left;
                } else {
                    System.out.print(cur.val + "\t");
                    leftRight.right = null;
                    cur = cur.right;
                }
            } else {
                System.out.print(cur.val + "\t");
                cur = cur.right;
            }
        }
    }


    /*
                 1
               /   \
              2     3
             / \   /  \
            4   7 5    6
         */

    /**
     * 后序遍历-Morris解法
     *
     * @param node 节点
     */
    private static void postOrderMorris(TreeNode node) {
        TreeNode cur = node, leftRight = null;
        while (cur != null) {
            if (cur.left != null) {
                leftRight = cur.left;
                while (leftRight.right != null && leftRight.right != cur) {
                    leftRight = leftRight.right;
                }
                if (leftRight.right == null) {
                    leftRight.right = cur;
                    cur = cur.left;
                } else {
                    leftRight.right = null;
                    printEdge(cur.left);
                    cur = cur.right;
                }
            } else {
                cur = cur.right;
            }
        }
        printEdge(node);
    }

    private static void printEdge(TreeNode node) {
        node = reverseRightTree(node);
        TreeNode p = node;
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.right;
        }
        reverseRightTree(node);
    }

    private static TreeNode reverseRightTree(TreeNode node) {
        if (node.right == null) return node;
        TreeNode head = null, p = node, tmp;
        while (p != null) {
            tmp = p.right;
            p.right = head;
            head = p;
            p = tmp;
        }
        return head;
    }
}
