package com.shanhai.dataStructe._12binarySearchTree;

import java.util.*;

/**
 * 二叉搜索树
 *
 * @param <V>
 */
@SuppressWarnings("all")
public class BinarySearchTree<K, V> {
    private BSTNode<K, V> root;
    private Comparator<K> comparator;

    /*=============== 构造方法 ===============*/
    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    /*=============== get(K key) ===============*/
    public V get(K key) {
        return doGetNoRec(key);
    }

    private V doGetRec(BSTNode<K, V> root, K key) {
        if (root == null) return null; // 没找到
        int compare = compare(key, root.key);
        if (compare > 0) {
            return doGetRec(root.right, key); // 向右找
        } else if (compare < 0) {
            return doGetRec(root.left, key); // 向左找
        }
        return root.value;
    }

    private V doGetNoRec(K key) {
        BSTNode<K, V> node = root;
        while (node != null) {
            int compare = compare(key, node.key);
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                return node.value;
            }
        }
        return null;
    }

    /*=============== put(K key, V value) ===============*/
    public void put(K key, V value) {
        //root = doPutRec(root, key, value);
        doPutNoRec(key, value);
    }

    private BSTNode<K, V> doPutRec(BSTNode<K, V> node, K key, V value) {
        // null表示此BST中无对应key值的节点，新建一个
        if (node == null) return new BSTNode<>(key, value);
        int compare = compare(key, node.key);
        if (compare == 0) {
            // 根据key找到对应的节点，覆盖value值
            node.value = value;
        } else if (compare > 0) {
            node.right = doPutRec(node.right, key, value);
        } else {
            node.left = doPutRec(node.left, key, value);
        }
        return node;
    }

    private void doPutNoRec(K key, V value) {
        BSTNode<K, V> node = root;
        BSTNode<K, V> parent = null;
        int compare = 0;
        while (node != null) {
            parent = node;
            compare = compare(key, node.key);
            if (compare == 0) {
                node.value = value; // key存在则覆盖value
                return;
            } else if (compare > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        BSTNode<K, V> newNode = new BSTNode<>(key, value);
        if (parent == null) { // BST中无节点
            root = newNode;
        } else if (compare > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
    }

    /*=============== remove(K key) ===============*/
    public V remove(K key) {
        List<V> result = new ArrayList<>();
        root = doRemoveRec(root, key, result);
        return result.isEmpty() ? null : result.get(0);
        //return doRemoveNoRec(key);
    }

    private BSTNode<K, V> doRemoveRec(BSTNode<K, V> node, K key, List<V> result) {
        if (node == null) return null;
        int compare = compare(key, node.key);
        if (compare > 0) {
            node.right = doRemoveRec(node.right, key, result);
            return node;
        }
        if (compare < 0) {
            node.left = doRemoveRec(node.left, key, result);
            return node;
        }
        result.add(node.value);
        if (node.left != null && node.right != null) {
            BSTNode<K, V> s = successorNode(node.key);
            s.right = doRemoveRec(node.right, s.key, new ArrayList<>());
            s.left = node.left;
            return s;
        }
        return node.left == null ? node.right : node.left;
    }

    private V doRemoveNoRec(K key) {
        BSTNode<K, V> delNode = node(key);
        if (delNode == null) return null;
        BSTNode<K, V> parent = parent(key);
        V value = delNode.value;
        BSTNode<K, V> tempNode;
        if (delNode.left != null && delNode.right != null) {
            // 删除度为2的节点，用前驱或者后继节点覆盖删除节点，后处理删除前驱或者后继节点
            tempNode = successorNode(delNode.key);
            parent = parent(tempNode.key);
            delNode.key = tempNode.key;
            delNode.value = tempNode.value;
            delNode = tempNode;
        }
        if (delNode.left == null) {
            tempNode = delNode.right;
        } else if (delNode.right == null) {
            tempNode = delNode.left;
        } else {
            tempNode = null;
        }
        delNode.left = null;
        delNode.right = null;
        if (parent == null) {
            root = tempNode;
        } else if (parent.left == delNode) {
            parent.left = tempNode;
        } else {
            parent.right = tempNode;
        }
        return value;
    }

    /*=============== 前驱后继 ===============*/
    public V predecessor(K key) {
        BSTNode<K, V> node = predecessorNode(key);
        return node == null ? null : node.value;
    }

    private BSTNode<K, V> predecessorNode(K key) {
        BSTNode<K, V> node = root;
        BSTNode<K, V> ancestorFromLeft = null;
        while (node != null) {
            int compare = compare(key, node.key);
            if (compare > 0) {
                ancestorFromLeft = node;
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                break;
            }
        }
        if (node == null) return null;
        // 节点有左孩子，找左孩子所在的子树中最大的节点(即左孩子最右)
        if (node.left != null) return doMaxNoRec(node.left);
        // 节点无左孩子
        return ancestorFromLeft;
    }

    public V successor(K key) {
        BSTNode<K, V> node = successorNode(key);
        return node == null ? null : node.value;
    }

    private BSTNode<K, V> successorNode(K key) {
        BSTNode<K, V> node = root;
        BSTNode<K, V> ancestorFromRight = null;
        while (node != null) {
            int compare = compare(key, node.key);
            if (compare < 0) {
                ancestorFromRight = node;
                node = node.left;
            } else if (compare > 0) {
                node = node.right;
            } else {
                break;
            }
        }
        if (node == null) return null;
        // 节点有右孩子，找右孩子所在的子树中最小的节点(即右孩子最左)
        if (node.right != null) return doMinNoRec(node.right);
        // 节点无右孩子
        return ancestorFromRight;
    }

    /*=============== min() ===============*/
    public V min() {
        BSTNode<K, V> node = doMinNoRec(root);
        return node == null ? null : node.value;
    }

    private BSTNode<K, V> doMinRec(BSTNode<K, V> root) {
        if (root == null) return null;
        // 左边已走到头
        if (root.left == null) return root;
        return doMinRec(root.left);
    }

    private BSTNode<K, V> doMinNoRec(BSTNode<K, V> root) {
        if (root == null) return null;
        BSTNode<K, V> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /*=============== max() ===============*/
    public V max() {
        BSTNode<K, V> node = doMaxNoRec(root);
        return node == null ? null : node.value;
    }

    private BSTNode<K, V> doMaxRec(BSTNode<K, V> root) {
        if (root == null) return null;
        // 右边已走到头
        if (root.right == null) return root;
        return doMaxRec(root.right);
    }

    private BSTNode<K, V> doMaxNoRec(BSTNode<K, V> root) {
        if (root == null) return null;
        BSTNode<K, V> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /*=============== less、greater、between ===============*/
    public List<V> less(K key) { // LNR
        List<V> result = new ArrayList<>();
        BSTNode<K, V> node = root;
        Deque<BSTNode<K, V>> stack = new LinkedList<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (compare(key, pop.key) > 0) {
                    result.add(pop.value);
                } else {
                    break;
                }
                node = pop.right;
            }
        }
        return result;
    }

    public List<V> greater(K key) { // RNL
        List<V> result = new ArrayList<>();
        BSTNode<K, V> node = root;
        Deque<BSTNode<K, V>> stack = new LinkedList<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.right;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (compare(key, pop.key) < 0) {
                    result.add(pop.value);
                } else {
                    break;
                }
                node = pop.left;
            }
        }
        return result;
    }

    public List<V> between(K key1, K key2) {
        List<V> result = new ArrayList<>();
        BSTNode<K, V> node = root;
        Deque<BSTNode<K, V>> stack = new LinkedList<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (compare(key1, pop.key) <= 0 && compare(key2, pop.key) >= 0) {
                    result.add(pop.value);
                } else if (compare(key2, pop.key) < 0) {
                    break;
                }
                node = pop.right;
            }
        }
        return result;
    }

    /*=============== node(K key)、parent(K key) ===============*/
    private BSTNode<K, V> node(K key) {
        BSTNode<K, V> node = root;
        while (node != null) {
            int compare = compare(key, node.key);
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    private BSTNode<K, V> parent(K key) {
        BSTNode<K, V> node = root;
        BSTNode<K, V> parent = null;
        while (node != null) {
            int compare = compare(key, node.key);
            if (compare == 0) {
                break;
            } else if (compare > 0) {
                parent = node;
                node = node.right;
            } else {
                parent = node;
                node = node.left;
            }
        }
        return parent;
    }

    /*=============== compare、isSameTree、BSTNode ===============*/
    private int compare(K k1, K k2) {
        return (comparator == null) ? ((Comparable<K>) k1).compareTo(k2)
                : comparator.compare(k1, k2);
    }

    public boolean isSameTree(BinarySearchTree<K, V> tree) {
        return isSameTree(this.root, tree.root);
    }

    private boolean isSameTree(BSTNode<K, V> tree1, BSTNode<K, V> tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        if (compare(tree1.key, tree2.key) != 0) {
            return false;
        }
        return isSameTree(tree1.left, tree2.left) && isSameTree(tree1.right, tree2.right);
    }

    private static class BSTNode<K, V> {
        K key;
        V value;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        BSTNode(K key) {
            this.key = key;
        }

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}