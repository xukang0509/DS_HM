package com.shanhai.dataStructe._15btree;

import java.util.Arrays;

@SuppressWarnings("all")
public class BTree {
    private final int MIN_KEY_NUMBER; // 最小key的数目
    private final int MAX_KEY_NUMBER; // 最大key的数目

    public BNode root;
    private int t; // 树中节点最小度数

    public BTree() {
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new BNode(t);
        MIN_KEY_NUMBER = t - 1;
        MAX_KEY_NUMBER = 2 * t - 1;
    }

    // 判断key是否存在
    public boolean contains(int key) {
        return root.get(key) != null;
    }

    // 新增
    public void put(int key) {
        doPut(root, key, null, 0);
    }

    private void doPut(BNode node, int key, BNode parent, int index) {
        int i = 0;
        while (i < node.keyNumber && node.keys[i] < key) {
            i++;
        }
        if (found(node, key, i)) {
            return; // 更新
        }
        // 此时 i 未插入位置
        if (node.leaf) {
            node.insertKey(key, i);
        } else {
            doPut(node.children[i], key, node, i);
        }
        // 上限
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
    }

    public void split(BNode left, BNode parent, int index) {
        if (parent == null) { // 分裂的是根节点
            BNode newNode = new BNode(t);
            newNode.leaf = false;
            newNode.insertChild(left, 0);
            this.root = newNode;
            parent = newNode;
        }
        // 1.创建 right 节点，把 left 中 t 之后的 key 和 child 移动过去
        BNode right = new BNode(t);
        right.leaf = left.leaf;
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        if (!left.leaf) { // 分裂节点是非叶子的情况
            System.arraycopy(left.children, t, right.children, 0, t);
        }
        right.keyNumber = t - 1;
        left.keyNumber = t - 1;
        // 2.中间的 key(t-1处) 插入到父节点
        int mid = left.keys[t - 1];
        parent.insertKey(mid, index);
        // 3.right 节点作为父节点的孩子
        parent.insertChild(right, index + 1);
    }

    // 删除
    public void remove(int key) {
        doRemove(root, key, null, 0);
    }

    private void doRemove(BNode node, int key, BNode parent, int index) {
        int i = 0;
        while (i < node.keyNumber && node.keys[i] < key) {
            i++;
        }
        // i 找到；代表待删除 key 的索引
        // i 没找到；代表到第i个孩子继续查找
        if (node.leaf) {
            if (!found(node, key, i)) {
                // case1: 当前节点是叶子，没找到
                return;
            } else {
                // case2: 当前节点是叶子，找到
                node.removeKey(i);
            }
        } else {
            if (!found(node, key, i)) {
                // case3: 当前节点不是叶子，没找到
                doRemove(node.children[i], key, node, i);
            } else {
                // case4: 当前节点不是叶子，找到
                // 1 查找后继 key
                BNode s = node.children[i + 1];
                while (!s.leaf) {
                    s = s.children[0];
                }
                int sKey = s.keys[0];
                // 2 替换待删除key
                node.keys[i] = sKey;
                // 3 删除后继 key
                doRemove(node.children[i + 1], sKey, node, i + 1);
            }
        }
        if (node.keyNumber < MIN_KEY_NUMBER) {
            // 调整平衡 case5；case6
            balance(parent, node, index);
        }
    }

    private void balance(BNode parent, BNode node, int index) {
        if (node == root) {
            // case 6 根节点
            if (root.keyNumber == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }
        BNode leftSibling = parent.childLeftSibling(index);
        BNode rightSibling = parent.childRightSibling(index);
        // case 5-1 左边富裕，右旋
        if (leftSibling != null && leftSibling.keyNumber > MIN_KEY_NUMBER) {
            // a.父节点中前驱的 key 旋转下来
            node.insertKey(parent.keys[index - 1], 0);
            // b.leftSibling 中最大的孩子换爹
            if (!leftSibling.leaf) {
                node.insertChild(leftSibling.removeRightmostChild(), 0);
            }
            // c.leftSibling 中最大的 key 旋转上去
            parent.keys[index - 1] = leftSibling.removeRightmostKey();
            return;
        }
        // case 5-2 右边富裕，左旋
        if (rightSibling != null && rightSibling.keyNumber > MIN_KEY_NUMBER) {
            // a.父节点中后继的 key 旋转下来
            node.insertKey(parent.keys[index], node.keyNumber);
            // b.rightSibling 中最小的孩子换爹
            if (!rightSibling.leaf) {
                node.insertChild(rightSibling.removeLeftmostChild(), node.keyNumber);
            }
            // c.rightSibling 中最小的 key 旋转上去
            parent.keys[index] = rightSibling.removeLeftmostKey();
            return;
        }
        // case 5-3 两边都不够借，向左合并
        if (leftSibling != null) {
            // 向左兄弟合并
            parent.removeChild(index);
            leftSibling.insertKey(parent.removeKey(index - 1), leftSibling.keyNumber);
            node.moveToTarget(leftSibling);
        } else {
            // 向自己合并
            parent.removeChild(index + 1);
            node.insertKey(parent.removeKey(index), node.keyNumber);
            rightSibling.moveToTarget(node);
        }
    }

    private boolean found(BNode node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }

    public void travel() {
        doTravel(root);
    }

    public void doTravel(BNode node) {
        if (node == null) {
            return;
        }
        int i = 0;
        for (; i < node.keyNumber; i++) {
            doTravel(node.children[i]);
            System.out.println(node.keys[i]);
        }
        doTravel(node.children[i]);
    }

    public static class BNode {
        public int[] keys;                     // 关键字
        public BNode[] children;               // 孩子
        public int keyNumber;                  // 有效关键字数目
        public boolean leaf = true;            // 是否是叶子节点
        public int t;                          // 最小度数(最小孩子数)

        public BNode(int t) {
            this.t = t;
            this.children = new BNode[2 * t];
            this.keys = new int[2 * t - 1];
        }

        public BNode(int[] keys) {
            this.keys = keys;
        }

        // 多路查找
        public BNode get(int key) {
            int i = 0;
            while (i < keyNumber && keys[i] < key) {
                i++;
            }
            if (i < keyNumber && keys[i] == key) {
                return this;
            }
            if (leaf) return null;
            return children[i].get(key);
        }

        // 先调用insertKey，后调用insertChild
        // 向 keys 指定索引 index 处插入 key
        void insertKey(int key, int index) {
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }

        // 向 children 指定索引处插入 child
        void insertChild(BNode child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }

        // 移除指定 index 处的key
        int removeKey(int index) {
            int key = keys[index];
            System.arraycopy(keys, index + 1, keys, index, --keyNumber - index);
            return key;
        }

        // 移除最左边的key
        int removeLeftmostKey() {
            return removeKey(0);
        }

        // 移除最右边的key
        int removeRightmostKey() {
            return removeKey(keyNumber - 1);
        }

        // 移除指定 index 的 child
        BNode removeChild(int index) {
            BNode child = children[index];
            System.arraycopy(children, index + 1, children, index, keyNumber - index);
            children[keyNumber] = null; // help GC
            return child;
        }

        // 移除最左边的child
        BNode removeLeftmostChild() {
            return removeChild(0);
        }

        // 移除最右边的child
        BNode removeRightmostChild() {
            return removeChild(keyNumber);
        }

        // index 孩子处左边的兄弟
        BNode childLeftSibling(int index) {
            return index > 0 ? children[index - 1] : null;
        }

        // index 孩子处右边的兄弟
        BNode childRightSibling(int index) {
            return index == keyNumber ? null : children[index + 1];
        }

        // 复制当前节点的所有 key 和 child 到 target
        void moveToTarget(BNode target) {
            int start = target.keyNumber;
            if (!leaf) {
                for (int i = 0; i <= keyNumber; i++) {
                    target.children[start + i] = children[i];
                }
            }
            for (int i = 0; i < keyNumber; i++) {
                target.keys[target.keyNumber++] = keys[i];
            }
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }
    }
}
