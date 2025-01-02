package com.shanhai.dataStructe._18graph.unionFind;

import java.util.Arrays;

// Quick Union -- 基于 size 的优化
public class QuickUnionSize extends QuickUnion {
    private int[] sizes;

    public QuickUnionSize(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        Arrays.fill(sizes, 1);
    }

    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        if (sizes[p1] < sizes[p2]) {
            parents[p1] = p2;
            sizes[p2] += sizes[p1];
        } else {
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        }
    }
}
