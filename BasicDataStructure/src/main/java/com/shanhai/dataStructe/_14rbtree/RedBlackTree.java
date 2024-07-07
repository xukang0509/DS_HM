package com.shanhai.dataStructe._14rbtree;

import java.util.Comparator;

@SuppressWarnings("all")
public class RedBlackTree<K, V> {
    private RBNode<K, V> root;
    private Comparator<K> comparator;

    public RedBlackTree() {
    }

    public RedBlackTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    /**
     * 新增或更新
     * <br>
     * 正常增、遇到红红不平衡进行调整
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        RBNode<K, V> p = root;
        RBNode<K, V> parent = null;
        int compare = 0;
        while (p != null) {
            parent = p;
            compare = compare(key, p.key);
            if (compare > 0) {
                p = p.right;
            } else if (compare < 0) {
                p = p.left;
            } else {
                p.value = value; // 更新
                return;
            }
        }
        RBNode<K, V> newNode = new RBNode<>(key, value);
        if (parent == null) {
            root = newNode;
        } else if (compare > 0) {
            parent.right = newNode;
            newNode.parent = parent;
        } else {
            parent.left = newNode;
            newNode.parent = parent;
        }
        fixRedRed(newNode);
    }

    private void fixRedRed(RBNode<K, V> node) {
        // case1：插入节点为根节点，将根节点变黑
        if (root == node) {
            node.color = Color.BLACK;
            return;
        }
        // case2：插入节点的父节点为黑色节点，无需调整
        if (isBlack(node.parent)) {
            return;
        }
        // 以下为插入节点的父节点为红色，触发红红冲突
        RBNode<K, V> parent = node.parent;
        RBNode<K, V> grandParent = parent.parent;
        RBNode<K, V> uncle = node.uncle();
        // case3：叔叔为红色时，需要将父亲、叔叔变黑、祖父变红，然后对祖父做递归处理
        if (isRed(uncle)) {
            parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            grandParent.color = Color.RED;
            fixRedRed(grandParent);
            return;
        }
        // case4：以下叔叔为黑色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // L
                parent.color = Color.BLACK;
            } else { // R
                leftRotate(parent);
                node.color = Color.BLACK;
            }
            grandParent.color = Color.RED;
            rightRotate(grandParent);
        } else { // R
            if (!node.isLeftChild()) { // R
                parent.color = Color.BLACK;
            } else { // L
                rightRotate(parent);
                node.color = Color.BLACK;
            }
            grandParent.color = Color.RED;
            leftRotate(grandParent);
        }
        /*
        // case4：以下叔叔为黑色
        if (parent.isLeftChild() && node.isLeftChild()) { // LL
            parent.color = Color.BLACK;
            grandParent.color = Color.RED;
            rightRotate(grandParent);
        } else if (parent.isLeftChild() && !node.isLeftChild()) { // LR
            leftRotate(parent);
            node.color = Color.BLACK;
            grandParent.color = Color.RED;
            rightRotate(grandParent);
        } else if (!parent.isLeftChild() && !node.isLeftChild()) { // RR
            parent.color = Color.BLACK;
            grandParent.color = Color.RED;
            leftRotate(grandParent);
        } else { // RL
            rightRotate(parent);
            node.color = Color.BLACK;
            grandParent.color = Color.RED;
            leftRotate(grandParent);
        }
        */
    }

    /**
     * 删除
     * 正常删除，会用到李代桃僵技巧，遇到黑黑不平衡进行调整
     *
     * @param key 键
     */
    public void remove(K key) {
        RBNode<K, V> deleted = findNode(key);
        if (deleted == null) return;
        doRemove(deleted);
    }

    private void doRemove(RBNode<K, V> deleted) {
        RBNode<K, V> replaced = findReplaced(deleted);
        RBNode<K, V> parent = deleted.parent;
        // 没有孩子
        if (replaced == null) {
            if (deleted == root) {
                // case 1：删除的是根节点
                root = null;
            } else {
                if (isBlack(deleted)) {
                    // 删除黑色叶子，复杂调整
                    fixDoubleBlack(deleted);
                } // 删除红色叶子，无需任何调整
                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.parent = null;
            }
            return;
        }
        // 有一个孩子
        if (deleted.left == null || deleted.right == null) {
            if (deleted == root) {
                // case 1：删除的是根节点
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right = null;
                replaced.parent = null;
            } else {
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;
                if (isBlack(deleted) && isBlack(replaced)) {
                    // 复杂处理
                    // TODO 实际不会有这种情况 因为只有一个孩子时 被删除节点是黑色 那么剩余节点只能是红色不会触发双黑
                    fixDoubleBlack(replaced);
                } else {
                    // case 2：删的是黑，剩下的是红
                    replaced.color = Color.BLACK;
                }
            }
            return;
        }
        // case 0：有两个孩子 ===> 有一个孩子 或 没有孩子
        deleted.key = replaced.key;
        deleted.value = replaced.value;
        doRemove(replaced);
    }

    // 处理双黑(case3、case4、case5)
    private void fixDoubleBlack(RBNode<K, V> node) {
        if (root == node) return;
        RBNode<K, V> parent = node.parent;
        RBNode<K, V> sibling = node.sibling();
        if (isRed(sibling)) {
            // case 3：兄弟节点是红色
            if (node.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = Color.RED;
            sibling.color = Color.BLACK;
            fixDoubleBlack(node);
            return;
        }
        // 兄弟节点是黑色
        if (sibling != null) {
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // case 4：兄弟节点为黑色，两个侄子都为黑
                sibling.color = Color.RED;
                if (isRed(parent)) {
                    parent.color = Color.BLACK;
                } else {
                    fixDoubleBlack(parent);
                }
            } else {
                // case 5：兄弟节点为黑色，侄子有红色
                if (sibling.isLeftChild()) { // L
                    if (isRed(sibling.left)) { // L
                        rightRotate(parent);
                        sibling.left.color = Color.BLACK;
                        sibling.color = parent.color;
                    } else { // R
                        sibling.right.color = parent.color;
                        leftRotate(sibling);
                        rightRotate(parent);
                    }
                } else { // R
                    if (isRed(sibling.right)) { // R
                        leftRotate(parent);
                        sibling.right.color = Color.BLACK;
                        sibling.color = parent.color;
                    } else { // L
                        sibling.left.color = parent.color;
                        rightRotate(sibling);
                        leftRotate(parent);
                    }
                }
                parent.color = Color.BLACK;
            }
        } else {
            // TODO 实际不会出现，触发双黑后，兄弟节点不会为 null
            fixDoubleBlack(parent);
        }
    }

    // 根据KEY查找节点
    private RBNode<K, V> findNode(K key) {
        RBNode<K, V> p = root;
        while (p != null) {
            int compare = compare(key, p.key);
            if (compare > 0) {
                p = p.right;
            } else if (compare < 0) {
                p = p.left;
            } else {
                return p;
            }
        }
        return null;
    }

    // 找到删除节点后剩余的节点
    private RBNode<K, V> findReplaced(RBNode<K, V> deleted) {
        /*
        // 此代码可不执行
        if (deleted.left == null && deleted.right == null) {
            return null;
        }
        */
        if (deleted.left == null) {
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }
        RBNode<K, V> s = deleted.right;
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }

    public boolean contains(K key) {
        return findNode(key) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // 右旋
    private void rightRotate(RBNode<K, V> parent) {
        RBNode<K, V> left = parent.left;
        RBNode<K, V> leftRight = left.right;
        left.right = parent;
        parent.left = leftRight;
        afterRotate(parent, left, leftRight);
    }

    // 左旋
    private void leftRotate(RBNode<K, V> parent) {
        RBNode<K, V> right = parent.right;
        RBNode<K, V> rightLeft = right.left;
        right.left = parent;
        parent.right = rightLeft;
        afterRotate(parent, right, rightLeft);
    }

    // 旋转之后的操作：1、parent的处理 2、旋转后新的父子关系
    private void afterRotate(RBNode<K, V> parent, RBNode<K, V> child, RBNode<K, V> childChild) {
        RBNode<K, V> grandParent = parent.parent;
        child.parent = grandParent;
        parent.parent = child;
        if (childChild != null) {
            childChild.parent = parent;
        }
        if (grandParent == null) {
            root = child;
        } else if (grandParent.left == parent) {
            grandParent.left = child;
        } else {
            grandParent.right = child;
        }
    }

    // 判断红
    private boolean isRed(RBNode<K, V> node) {
        return node != null && node.color == Color.RED;
    }

    // 判断黑
    private boolean isBlack(RBNode<K, V> node) {
        // return !isRed(node);
        return node == null || node.color == Color.BLACK;
    }

    private int compare(K k1, K k2) {
        return comparator != null ? comparator.compare(k1, k2) :
                ((Comparable) k1).compareTo(k2);
    }

    private enum Color {
        RED, BLACK
    }

    private static class RBNode<K, V> {
        K key;
        V value;
        RBNode<K, V> left;
        RBNode<K, V> right;
        RBNode<K, V> parent;
        Color color = Color.RED;

        public RBNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // 是否是左孩子
        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        // 叔叔
        RBNode<K, V> uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            if (parent.isLeftChild()) {
                return parent.parent.right;
            }
            return parent.parent.left;
        }

        // 兄弟
        RBNode<K, V> sibling() {
            if (parent == null) return null;
            if (this.isLeftChild()) {
                return parent.right;
            }
            return parent.left;
        }
    }
}