package com.shanhai.dataStructe._04Recursion;

public class TailRecursion {
    public static void main(String[] args) {
        //System.out.println(sum1(12000));
        System.out.println(sum2(100000, 0));
    }

    private static long sum1(long n) {
        if (n == 1) return 1;
        return n + sum1(n - 1);
    }

    private static long sum2(long n, long accumulator) {
        if (n == 1) return 1 + accumulator;
        return sum2(n - 1, accumulator + n);
    }
}
