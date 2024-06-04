package com.shanhai.dataStructe._01BinarySearch;

import com.shanhai.utils.Asserts;

/**
 * @description:
 * @author: xu
 * @date: 2024/5/11 23:17
 */
public class BasicBinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 6, 8, 10, 13, 15, 18, 20};
        for (int i = 0; i < arr.length; i++) {
            Asserts.test(binarySearch1(arr, arr[i]) == i);
        }

        for (int i = 0; i < arr.length; i++) {
            Asserts.test(binarySearch2(arr, arr[i]) == i);
        }
    }

    private static int binarySearch1(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < arr[m]) { // 在左边
                j = m - 1;
            } else if (arr[m] < target) { // 在右边
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    private static int binarySearch2(int[] arr, int target) {
        int i = 0, j = arr.length;
        while (i < j) {
            int m = (i + j) >>> 1;
            if (target < arr[m]) { // 在左边
                j = m;
            } else if (arr[m] < target) { // 在右边
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }
}
