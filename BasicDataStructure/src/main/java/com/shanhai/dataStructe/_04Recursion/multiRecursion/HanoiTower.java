package com.shanhai.dataStructe._04Recursion.multiRecursion;

import java.util.LinkedList;

public class HanoiTower {

    private static LinkedList<Integer> a = new LinkedList<>();
    private static LinkedList<Integer> b = new LinkedList<>();
    private static LinkedList<Integer> c = new LinkedList<>();

    private static void init(int n) {
        for (int i = 1; i <= n; i++) {
            a.addFirst(i);
        }
    }

    private static void print() {
        System.out.println("=========================");
        System.out.println("a:" + a);
        System.out.println("b:" + b);
        System.out.println("c:" + c);
    }

    /**
     * @param n      要移动的圆盘数目
     * @param source 源
     * @param temp   借道
     * @param dest   目的
     */
    private static void move(int n,
                             LinkedList<Integer> source,
                             LinkedList<Integer> temp,
                             LinkedList<Integer> dest) {
        if (n == 0) return;
        move(n - 1, source, dest, temp);
        dest.addLast(source.removeLast());
        print();
        move(n - 1, temp, source, dest);
    }

    public static void main(String[] args) {
        init(3);
        print();
        move(3, a, b, c);
    }
}
