package com.shanhai.dataStructe._18graph.unionFind;

// Quick Union -- 基于rank的优化 -- 路径减半[Path Halving]
public class QuickUnionRank_PH extends QuickUnionRank {
    public QuickUnionRank_PH(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}
