package com.shanhai.dataStructe._11binaryTree;

import java.util.Deque;
import java.util.LinkedList;

public class TreeTraversalCommon {
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

        commonTreeTraversal(root);
    }

    private static void commonTreeTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;     // 当前节点
        TreeNode lastPop = null; // 最后一次弹栈的元素

        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                colorPrintln("前：" + cur.val, 31);
                // 待处理左子树
                cur = cur.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null) { // 没有右子树
                    colorPrintln("中：" + peek.val, 36);
                    lastPop = stack.pop();
                    colorPrintln("后：" + lastPop.val, 34);
                } else if (peek.right == lastPop) { // 右子树处理完成
                    lastPop = stack.pop();
                    colorPrintln("后：" + lastPop.val, 34);
                } else { // 待处理右子树
                    colorPrintln("中：" + peek.val, 36);
                    cur = peek.right;
                }
            }
        }
    }

    public static void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }
}
