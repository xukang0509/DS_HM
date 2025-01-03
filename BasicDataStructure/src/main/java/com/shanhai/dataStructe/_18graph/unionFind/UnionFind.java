package com.shanhai.dataStructe._18graph.unionFind;

public abstract class UnionFind {
    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        parents = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parents[i] = i;
        }
    }

    /**
     * 查找v所属的集合（根节点）
     */
    public abstract int find(int v);

    /**
     * 合并v1、v2所在的集合
     */
    public abstract void union(int v1, int v2);

    /**
     * 检查v1、v2是否属于同一个集合
     */
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /**
     * 边界检查：检查传入值的合法性
     */
    protected void rangeCheck(int v) {
        if (v < 0 || v >= parents.length)
            throw new IndexOutOfBoundsException("v is out of bounds");
    }
}
