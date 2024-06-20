package com.shanhai.dataStructe._11binaryTree;

import java.util.Deque;
import java.util.LinkedList;

public class ExpressionTree {
    /*
     * 中缀表达式           (2-1)*3
     * 后缀（逆波兰）表达式   21-3*
     *
     * 表达式树
     *       *
     *      / \
     *     -   3
     *    / \
     *   2   1
     */
    public TreeNode constructExpressionTree(String[] tokens) {
        Deque<TreeNode> stack = new LinkedList<>();
        for (String token : tokens) {
            switch (token) {
                case "+", "-", "*", "/" -> { // 运算符
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    TreeNode parent = new TreeNode(token);
                    parent.left = left;
                    parent.right = right;
                    stack.push(parent);
                }
                // 数字
                default -> stack.push(new TreeNode(token));
            }
        }
        return stack.peek();
    }

    static class TreeNode {
        public String val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, String val, TreeNode right) {
            this.left = left;
            this.val = val;
            this.right = right;
        }

        @Override
        public String toString() {
            return this.val;
        }
    }
}
