package com.shanhai.dataStructe._04Recursion.multiRecursion;

import com.shanhai.utils.Times;
import org.junit.jupiter.api.Test;


public class FibonacciTest {
    public static int fibonacci(int n) {
        if (n == 0 || n == 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @Test
    public void test1() {
        Times.test("fibonacci(50)", () -> System.out.println(fibonacci(50)));
    }

    @Test
    public void test() {
        System.out.println("fibonacci(10) = " + fibonacci(10));
    }
}
