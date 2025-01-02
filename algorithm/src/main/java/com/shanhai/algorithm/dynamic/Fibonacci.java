package com.shanhai.algorithm.dynamic;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(4));
        System.out.println(fibonacci(5));
        System.out.println(fibonacci(6));
        System.out.println(fibonacci(7));
        System.out.println(fibonacci(8));
        System.out.println(fibonacci(9));
        System.out.println(fibonacci(10));
    }

    public static int fibonacci(int n) {
        if (n == 0 || n == 1) return n;
        int first = 0, second = 1;
        for (int i = 2; i <= n; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }
}
