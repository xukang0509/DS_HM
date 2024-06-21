package com.shanhai.dataStructe._13avltree;

import java.util.Comparator;

/**
 * <h3>AVL 树</h3>
 * <ul>
 *     <li>二叉搜索树在插入和删除时，节点可能失衡</li>
 *     <li>如果在插入和删除时通过旋转, 始终让二叉搜索树保持平衡, 称为自平衡的二叉搜索树</li>
 *     <li>AVL 是自平衡二叉搜索树的实现之一</li>
 * </ul>
 */
@SuppressWarnings("all")
public class AVLTree<K, V> {
    private AVLNode<K, V> root;
    private Comparator<K> comparator;

    public AVLTree() {
    }

    public AVLTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    public void put(K key, V value) {
        root = doPut(root, key, value);
    }

    private AVLNode<K, V> doPut(AVLNode<K, V> node, K key, V value) {
        if (node == null) return new AVLNode<>(key, value);
        int compare = compare(key, node.key);
        if (compare > 0) {
            node.right = doPut(node.right, key, value);
        } else if (compare < 0) {
            node.left = doPut(node.left, key, value);
        } else {
            node.value = value;
            return node;
        }
        updateHeight(node);
        return balance(node);
    }

    public void remove(K key) {
        root = doRemove(root, key);
    }

    private AVLNode<K, V> doRemove(AVLNode<K, V> node, K key) {
        if (node == null) return null;
        int compare = compare(key, node.key);
        if (compare > 0) {
            node.right = doRemove(node.right, key);
        } else if (compare < 0) {
            node.left = doRemove(node.left, key);
        } else {
            if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                AVLNode<K, V> s = node.right;
                while (s.left != null) s = s.left;
                s.right = doRemove(node.right, s.key);
                s.left = node.left;
                node = s;
            }
        }
        if (node == null) return null;
        updateHeight(node);
        return balance(node);
    }


    /**
     * 检查节点是否失衡，重新平衡AVLTree
     *
     * @param node
     * @return
     */
    private AVLNode<K, V> balance(AVLNode<K, V> node) {
        if (node == null) return null;
        int bf = bf(node);
        if (bf > 1 && bf(node.left) >= 0) { // LL
            node = rightRotate(node);
        } else if (bf > 1 && bf(node.left) < 0) { // LR
            node = leftRightRotate(node);
        } else if (bf < -1 && bf(node.right) <= 0) { // RR
            node = leftRotate(node);
        } else if (bf < -1 && bf(node.right) > 0) { // RL
            node = rightLeftRotate(node);
        }
        return node;
    }

    /**
     * 右旋(LL)
     *
     * @param parent
     * @return
     */
    private AVLNode<K, V> rightRotate(AVLNode<K, V> parent) {
        AVLNode<K, V> left = parent.left;
        AVLNode<K, V> leftRight = left.right;
        left.right = parent;
        parent.left = leftRight;
        updateHeight(parent);
        updateHeight(left);
        return left;
    }

    /**
     * 左旋(RR)
     *
     * @param parent
     * @return
     */
    private AVLNode<K, V> leftRotate(AVLNode<K, V> parent) {
        AVLNode<K, V> right = parent.right;
        AVLNode<K, V> rightLeft = right.left;
        right.left = parent;
        parent.right = rightLeft;
        updateHeight(parent);
        updateHeight(right);
        return right;
    }

    /**
     * 左右旋(LR)，先左旋左孩子，后右旋父节点
     *
     * @param parent
     * @return
     */
    private AVLNode<K, V> leftRightRotate(AVLNode<K, V> parent) {
        parent.left = leftRotate(parent.left);
        return rightRotate(parent);
    }

    /**
     * 右左旋(RL)，先右旋右孩子，后左旋父节点
     *
     * @param parent
     * @return
     */
    private AVLNode<K, V> rightLeftRotate(AVLNode<K, V> parent) {
        parent.right = rightRotate(parent.right);
        return leftRotate(parent);
    }


    // 求节点的高度
    private int height(AVLNode<K, V> node) {
        return node == null ? 0 : node.height;
    }

    // 更新高度（新增、删除、旋转）
    private void updateHeight(AVLNode<K, V> node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    // 平衡因子(Balance Factor) = 左子树高度 - 右子树高度
    private int bf(AVLNode<K, V> node) {
        return height(node.left) - height(node.right);
    }

    private int compare(K k1, K k2) {
        return comparator != null ? comparator.compare(k1, k2)
                : ((Comparable<? super K>) k1).compareTo(k2);
    }

    private static class AVLNode<K, V> {
        K key;
        V value;
        AVLNode<K, V> left;
        AVLNode<K, V> right;
        int height = 1;

        public AVLNode(K key) {
            this.key = key;
        }

        public AVLNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(K key, V value, AVLNode<K, V> left, AVLNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
