package com.shanhai.dataStructe._04Recursion.singelRecursion;

public class BinarySearch {
    public static void main(String[] args) {

    }

    public static int binarySearch(int[] a, int target) {
        return recursion(a, target, 0, a.length - 1);
    }

    private static int recursion(int[] a, int target, int l, int r) {
        if (l > r) return -1;
        int m = (l + r) >>> 1;
        if (a[m] < target)
            return recursion(a, target, m + 1, r);
        else if (target < a[m])
            return recursion(a, target, l, m - 1);
        else
            return m;
    }
}
