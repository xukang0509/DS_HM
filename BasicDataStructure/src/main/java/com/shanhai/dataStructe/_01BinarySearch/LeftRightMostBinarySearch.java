package com.shanhai.dataStructe._01BinarySearch;

/**
 * @description:
 * @author: xu
 * @date: 2024/5/11 23:17
 */
public class LeftRightMostBinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 4, 4, 5, 6, 7};

        System.out.println(binarySearchLeftMost(arr, 4));
        System.out.println(binarySearchRightMost(arr, 4));
    }

    private static int binarySearchLeftMost(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        int candidate = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < arr[m]) {
                j = m - 1;
            } else if (arr[m] < target) {
                i = m + 1;
            } else {
                candidate = m;  // 记录候选位置
                j = m - 1;      // 继续向左
            }
        }
        return candidate;
    }

    private static int binarySearchRightMost(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        int candidate = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < arr[m]) {
                j = m - 1;
            } else if (arr[m] < target) {
                i = m + 1;
            } else {
                candidate = m;  // 记录候选位置
                i = m + 1;      // 继续向右
            }
        }
        return candidate;
    }
}
