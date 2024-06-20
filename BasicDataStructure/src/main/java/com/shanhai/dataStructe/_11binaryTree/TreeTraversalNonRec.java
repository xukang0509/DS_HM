package com.shanhai.dataStructe._11binaryTree;

import java.util.Deque;
import java.util.LinkedList;

public class TreeTraversalNonRec {
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

        leverOrder(root); // 1 2 3 4 5 6
        System.out.println();
    }

    /**
     * 层次遍历
     *
     * @param root 根节点
     */
    private static void leverOrder(TreeNode root) {
        if (root == null) return;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            System.out.print(poll.val + "\t");
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }
    }

    /**
     * 前序遍历-非递归实现2
     *
     * @param node 节点
     */
    private static void preOrder(TreeNode node) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = node;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                System.out.print(cur.val + "\t");
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop().right;
            }
        }
    }

    /**
     * 前序遍历-非递归实现1
     *
     * @param node 节点
     */
    private static void preOrder1(TreeNode node) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.print(pop.val + "\t");
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
    }

    /**
     * 中序遍历-非递归实现
     *
     * @param node 节点
     */
    private static void inOrder(TreeNode node) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = node;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop();
                System.out.print(pop.val + "\t");
                cur = pop.right;
            }
        }
    }

    /**
     * 后序遍历-非递归实现
     *
     * @param node 节点
     */
    private static void postOrder(TreeNode node) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = node; // 当前元素
        TreeNode pop = null; // 最近一次弹栈的元素
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                    System.out.print(pop + "\t");
                } else {
                    cur = peek.right;
                }
            }
        }
    }
}
