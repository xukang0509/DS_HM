package com.shanhai.dataStructe._04Recursion.singelRecursion;

public class Factorial {

    public static void main(String[] args) {
        System.out.println("factorial(5) = " + factorial(5));
        System.out.println("factorial(1) = " + factorial(1));
        System.out.println("factorial(2) = " + factorial(2));
        System.out.println("factorial(3) = " + factorial(3));
        System.out.println("factorial(10) = " + factorial(10));
        System.out.println("factorial(9) = " + factorial(9));
    }


    private static long factorial(long n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

}
