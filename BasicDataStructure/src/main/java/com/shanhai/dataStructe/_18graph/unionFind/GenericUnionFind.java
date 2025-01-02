package com.shanhai.dataStructe._18graph.unionFind;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Quick Union + 基于 rank 的优化 + Path Halving
public class GenericUnionFind<V> {
    private Map<V, Node<V>> nodes;

    public GenericUnionFind() {
        nodes = new HashMap<>();
    }

    public GenericUnionFind(Collection<V> elements) {
        if (elements.isEmpty()) return;
        nodes = new HashMap<>(elements.size());
        for (V element : elements) {
            if (nodes.containsKey(element)) continue;
            nodes.put(element, new Node<>(element));
        }
    }

    public GenericUnionFind(V[] elements) {
        if (elements.length < 1) return;
        nodes = new HashMap<>(elements.length);
        for (V element : elements) {
            if (nodes.containsKey(element)) continue;
            nodes.put(element, new Node<>(element));
        }
    }

    public void makeSet(V v) {
        if (nodes.containsKey(v)) return;
        nodes.put(v, new Node<>(v));
    }

    public V find(V v) {
        Node<V> node = findNode(v);
        return node == null ? null : node.value;
    }

    public void union(V v1, V v2) {
        Node<V> p1Node = findNode(v1);
        Node<V> p2Node = findNode(v2);
        if (p1Node == null || p2Node == null) return;
        if (Objects.equals(p1Node.value, p2Node.value)) return;
        if (p1Node.rank < p2Node.rank) {
            p1Node.parent = p2Node;
        } else if (p1Node.rank > p2Node.rank) {
            p2Node.parent = p1Node;
        } else {
            p1Node.parent = p2Node;
            p2Node.rank += 1;
        }
    }

    public boolean isSame(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }

    // 找到v的根节点
    private Node<V> findNode(V v) {
        Node<V> vNode = nodes.get(v);
        if (vNode == null) return null;
        while (!Objects.equals(vNode.value, vNode.parent.value)) {
            vNode.parent = vNode.parent.parent;
            vNode = vNode.parent;
        }
        return vNode;
    }

    private static class Node<V> {
        V value;
        Node<V> parent = this;
        int rank = 1;

        Node(V value) {
            this.value = value;
        }
    }
}
