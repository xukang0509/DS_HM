package com.shanhai.dataStructe._04Recursion.multiRecursion;

import com.shanhai.utils.Times;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FibonacciMemTest {
    private static int fibonacci(int n, int[] cache) {
        if (cache[n] != -1) return cache[n];
        cache[n] = fibonacci(n - 1, cache) + fibonacci(n - 2, cache);
        return cache[n];
    }

    public static int fibonacci(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        return fibonacci(n, cache);
    }

    @Test
    public void test2() {
        Times.test("fibonacciMem(50)", () -> System.out.println(fibonacci(50)));
    }

    @Test
    public void test1() {
        System.out.println("fibonacci(1) = " + fibonacci(1));
        System.out.println("fibonacci(2) = " + fibonacci(2));
        System.out.println("fibonacci(3) = " + fibonacci(3));
        System.out.println("fibonacci(4) = " + fibonacci(4));
        System.out.println("fibonacci(5) = " + fibonacci(5));
        System.out.println("fibonacci(6) = " + fibonacci(6));
        System.out.println("fibonacci(7) = " + fibonacci(7));
        System.out.println("fibonacci(8) = " + fibonacci(8));
        System.out.println("fibonacci(9) = " + fibonacci(9));
        System.out.println("fibonacci(10) = " + fibonacci(10));
        System.out.println("fibonacci(11) = " + fibonacci(11));
        System.out.println("fibonacci(12) = " + fibonacci(12));
    }
}
