package com.shanhai.dataStructe._18graph.unionFind;

// Quick Union -- 基于rank的优化 -- 路径分裂[Path Spliting]
public class QuickUnionRank_PS extends QuickUnionRank {
    public QuickUnionRank_PS(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            int p = parents[v];
            parents[v] = parents[parents[v]];
            v = p;
        }
        return v;
    }
}
