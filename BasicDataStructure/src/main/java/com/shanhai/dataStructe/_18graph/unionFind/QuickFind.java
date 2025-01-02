package com.shanhai.dataStructe._18graph.unionFind;

public class QuickFind extends UnionFind {
    public QuickFind(int capacity) {
        super(capacity);
    }

    // O(1) 父节点都是根节点
    @Override
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    // O(n) 将 v1 所在集合的所有元素都嫁接到 v2 的父节点上
    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }
}
