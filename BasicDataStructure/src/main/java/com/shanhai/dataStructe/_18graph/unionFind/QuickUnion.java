package com.shanhai.dataStructe._18graph.unionFind;

public class QuickUnion extends UnionFind {
    public QuickUnion(int capacity) {
        super(capacity);
    }

    // O(logN) 通过parent链条不断地向上找，直到找到根节点
    @Override
    public int find(int v) {
        rangeCheck(v);
        while (parents[v] != v) {
            v = parents[v];
        }
        return v;
    }

    // O(logN)  将 v1 的根节点都嫁接到 v2 的根节点上
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        parents[p1] = p2;
    }
}
