package com.shanhai.dataStructe._01BinarySearch;

import com.shanhai.utils.Asserts;

/**
 * @description:
 * @author: xu
 * @date: 2024/5/11 23:17
 */
public class BalancedBinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 8, 10, 13, 15, 18, 20};

        for (int i = 0; i < arr.length; i++) {
            Asserts.test(balancedBinarySearch(arr, arr[i]) == i);
        }
    }

    private static int balancedBinarySearch(int[] arr, int target) {
        int i = 0, j = arr.length;
        // 待比较的元素个数 > 1
        while (1 < j - i) {
            int m = (i + j) >>> 1;
            if (target < arr[m]) { // 在左边
                j = m;
            } else { // 在右边
                i = m;
            }
        }
        return (arr[i] == target) ? i : -1;
    }
}
