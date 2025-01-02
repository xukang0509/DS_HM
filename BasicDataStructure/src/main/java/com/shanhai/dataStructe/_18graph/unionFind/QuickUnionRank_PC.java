package com.shanhai.dataStructe._18graph.unionFind;

// Quick Union -- 基于rank的优化 -- 路径压缩[Path Compression]
public class QuickUnionRank_PC extends QuickUnionRank {
    public QuickUnionRank_PC(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        if (v != parents[v]) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
