package com.shanhai.dataStructe._04Recursion.singelRecursion;

import java.util.Arrays;

public class BubbleTest {
    public static void main(String[] args) {
        int[] a = {3, 2, 6, 1, 8, 5, 4, 7};
        System.out.println(Arrays.toString(a));
        bubble(a);
        System.out.println(Arrays.toString(a));
    }

    public static void bubble(int[] a) {
        bubble(a, 0, a.length - 1);
    }

    private static void bubble(int[] a, int low, int high) {
        if (low == high) return;
        int j = low;
        for (int i = low; i < high; i++) {
            if (a[i] > a[i + 1]) {
                swap(a, i, i + 1);
                j = i;
            }
        }
        bubble(a, low, j);
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
