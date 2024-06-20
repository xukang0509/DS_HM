package com.shanhai.dataStructe._11binaryTree;

public class TreeTraversalRec {
    public static void main(String[] args) {
         /*
                1
               / \
              2   3
             /   / \
            4   5   6
         */
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );
        preOrder(root);
        System.out.println(); // 1 2 4 3 5 6

        inOrder(root);
        System.out.println(); // 4 2 1 5 3 6

        postOrder(root);
        System.out.println(); // 4 2 5 6 3 1
    }

    /**
     * 前序遍历
     *
     * @param node 节点
     */
    private static void preOrder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + "\t"); // 根
        preOrder(node.left); // 左
        preOrder(node.right); // 右
    }

    /**
     * 中序遍历
     *
     * @param node 节点
     */
    private static void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left); // 左
        System.out.print(node.val + "\t"); // 根
        inOrder(node.right); // 右
    }

    /**
     * 后序遍历
     *
     * @param node 节点
     */
    private static void postOrder(TreeNode node) {
        if (node == null) return;
        postOrder(node.left); // 左
        postOrder(node.right); // 右
        System.out.print(node.val + "\t"); // 根
    }
}
