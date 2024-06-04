package com.shanhai.dataStructe._04Recursion.singelRecursion;

import java.util.Arrays;

public class InsertSortTest {
    public static void main(String[] args) {
        int[] a = {3, 2, 6, 1, 8, 5, 4, 7};
        System.out.println(Arrays.toString(a));
        insertion(a);
        System.out.println(Arrays.toString(a));
    }

    public static void insertion(int[] a) {
        insertion(a, 1, a.length - 1);
    }

    private static void insertion(int[] a, int low, int high) {
        if (low == high) return;
        int t = a[low];
        int i;
        for (i = low - 1; i >= 0 && a[i] > t; i--) {
            a[i + 1] = a[i];
        }
        a[i + 1] = t;
        insertion(a, low + 1, high);
    }
}
