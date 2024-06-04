package com.shanhai.dataStructe._04Recursion.multiRecursion;

public class YHSanJiao1 {

    private static int element(int i, int j, int[][] cache) {
        if (cache[i][j] > 0) return cache[i][j];
        if (i == j || j == 0) {
            cache[i][j] = 1;
            return cache[i][j];
        }
        cache[i][j] = element(i - 1, j - 1, cache) + element(i - 1, j, cache);
        return cache[i][j];
    }

    private static void printSpace(int n, int i) {
        int num = 2 * (n - 1 - i);
        for (int j = 0; j < num; j++) {
            System.out.print(" ");
        }
    }

    private static void print(int n) {
        int[][] cache = new int[n][];
        for (int i = 0; i < n; i++) {
            cache[i] = new int[i + 1];
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", element(i, j, cache));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        print(10);
    }
}
