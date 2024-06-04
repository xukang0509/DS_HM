package com.shanhai.dataStructe._01BinarySearch;

import java.util.Arrays;

/**
 * @description:
 * @author: xu
 * @date: 2024/5/11 23:17
 */
public class LeftRightMostBinarySearchApplication {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 4, 4, 4, 6, 7};
        System.out.println("arr = " + Arrays.toString(arr));

        System.out.println("0: " + binarySearchLeftMost(arr, 0));
        System.out.println("1: " + binarySearchLeftMost(arr, 1));
        System.out.println("2: " + binarySearchLeftMost(arr, 2));
        System.out.println("3: " + binarySearchLeftMost(arr, 3));
        System.out.println("4: " + binarySearchLeftMost(arr, 4));
        System.out.println("5: " + binarySearchLeftMost(arr, 5));
        System.out.println("6: " + binarySearchLeftMost(arr, 6));
        System.out.println("7: " + binarySearchLeftMost(arr, 7));
        System.out.println("8: " + binarySearchLeftMost(arr, 8));

        System.out.println("===========================================");
        System.out.println("arr = " + Arrays.toString(arr));

        System.out.println("0: " + binarySearchRightMost(arr, 0));
        System.out.println("1: " + binarySearchRightMost(arr, 1));
        System.out.println("2: " + binarySearchRightMost(arr, 2));
        System.out.println("3: " + binarySearchRightMost(arr, 3));
        System.out.println("4: " + binarySearchRightMost(arr, 4));
        System.out.println("5: " + binarySearchRightMost(arr, 5));
        System.out.println("6: " + binarySearchRightMost(arr, 6));
        System.out.println("7: " + binarySearchRightMost(arr, 7));
        System.out.println("8: " + binarySearchRightMost(arr, 8));
    }

    private static int binarySearchLeftMost(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target <= arr[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i;
    }

    private static int binarySearchRightMost(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < arr[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i - 1;
    }
}
