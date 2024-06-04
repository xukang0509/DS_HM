package com.shanhai.dataStructe._04Recursion.multiRecursion;

public class YHSanJiao {

    public static int element(int i, int j) {
        if (i == j || j == 0) return 1;
        return element(i - 1, j - 1) + element(i - 1, j);
    }

    private static void printSpace(int n, int i) {
        int num = 2 * (n - 1 - i);
        for (int j = 0; j < num; j++) {
            System.out.print(" ");
        }
    }

    private static void print(int n) {
        for (int i = 0; i < n; i++) {
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", element(i, j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        print(10);
    }
}
