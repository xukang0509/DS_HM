package com.shanhai.dataStructe._18graph.unionFind;

import java.util.Arrays;

// Quick Union -- 基于 rank 的优化
public class QuickUnionRank extends QuickUnion {
    private int[] ranks;

    public QuickUnionRank(int capacity) {
        super(capacity);
        ranks = new int[capacity];
        Arrays.fill(ranks, 1);
    }

    @Override
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        if (ranks[p1] < ranks[p2]) {
            parents[p1] = p2;
        } else if (ranks[p1] > ranks[p2]) {
            parents[p2] = p1;
        } else {
            parents[p1] = p2;
            ranks[p2] += 1;
        }
    }
}
