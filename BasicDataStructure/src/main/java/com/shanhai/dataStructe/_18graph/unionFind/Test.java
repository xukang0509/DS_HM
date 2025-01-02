package com.shanhai.dataStructe._18graph.unionFind;

import com.shanhai.utils.Times;

public class Test {
    private static final int COUNT = 500_0000;

    public static void main(String[] args) {
        //testTime(new QuickFind(COUNT)); // 太慢,不测
        //testTime(new QuickUnion(COUNT)); // 太慢,不测
        testTime(new QuickUnionSize(COUNT)); // Quick Union 基于size的优化
        testTime(new QuickUnionRank(COUNT)); // Quick Union 基于rank的优化
        testTime(new QuickUnionRank_PC(COUNT)); // Quick Union 基于rank的优化 路径压缩
        testTime(new QuickUnionRank_PS(COUNT)); // Quick Union 基于rank的优化 路径分裂
        testTime(new QuickUnionRank_PH(COUNT)); // Quick Union 基于rank的优化 路径减半
        testTime(new GenericUnionFind<Integer>());
    }

    static void testTime(UnionFind uf) {
        Times.test(uf.getClass().getSimpleName(), new Times.Task() {
            @Override
            public void execute() {
                for (int i = 0; i < COUNT; i++) {
                    uf.union((int) (Math.random() * COUNT), (int) (Math.random() * COUNT));
                }
                for (int i = 0; i < COUNT; i++) {
                    uf.isSame((int) (Math.random() * COUNT), (int) (Math.random() * COUNT));
                }
            }
        });
    }

    static void testTime(GenericUnionFind<Integer> uf) {
        for (int i = 0; i < COUNT; i++) {
            uf.makeSet(i);
        }

        Times.test(uf.getClass().getSimpleName(), new Times.Task() {
            @Override
            public void execute() {
                for (int i = 0; i < COUNT; i++) {
                    uf.union((int) (Math.random() * COUNT), (int) (Math.random() * COUNT));
                }
                for (int i = 0; i < COUNT; i++) {
                    uf.isSame((int) (Math.random() * COUNT), (int) (Math.random() * COUNT));
                }
            }
        });
    }
}
