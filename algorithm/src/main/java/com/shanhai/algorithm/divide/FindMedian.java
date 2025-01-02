package com.shanhai.algorithm.divide;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xk
 * @since 2024-08-13 10:16
 */
public class FindMedian {
    public static void main(String[] args) {
        System.out.println(findMedian(new int[]{3, 1, 5, 4}));
        System.out.println(findMedian(new int[]{3, 1, 5, 4, 7, 8}));
        System.out.println(findMedian(new int[]{4, 5, 1}));
        System.out.println(findMedian(new int[]{4, 5, 1, 6, 3}));
    }

    // {3, 1, 5, 4}：偶数个，找升序下中间的两个数n1、n2，median = (n1 + n2) / 2
    // {4, 5, 1, 6, 3}：奇数个，找升序下中间的数n，median = n
    private static double findMedian(int[] array) {
        if ((array.length & 1) == 1) {
            // 数组长度为奇数
            return quickSelection(array, array.length >>> 1);
        } else {
            // 数组长度为偶数
            int n1 = quickSelection(array, (array.length >>> 1) - 1);
            int n2 = quickSelection(array, array.length >>> 1);
            return (double) (n1 + n2) / 2;
        }
    }

    /**
     * 找出数组array中排名第rank小的数
     *
     * @param array 数组
     * @param rank  数组中第rank小的数，从0开始
     * @return 数组中第rank小的数
     */
    private static int quickSelection(int[] array, int rank) {
        if (rank < 0 || rank >= array.length)
            throw new IllegalArgumentException();
        return quickSelection(array, 0, array.length - 1, rank);
    }

    private static int quickSelection(int[] array, int left, int right, int rank) {
        int partition = partition(array, left, right);
        if (partition == rank) {
            return array[partition];
        } else if (partition > rank) {
            return quickSelection(array, left, partition - 1, rank);
        } else {
            return quickSelection(array, partition + 1, right, rank);
        }
    }

    private static int partition(int[] array, int left, int right) {
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(array, left, idx);
        int pv = array[left];
        int l = left + 1, r = right;
        while (l <= r) {
            // l 从左向右找大的或者相等的
            while (l <= r && array[l] < pv) l++;
            // r 从右向左找小的或者相等的
            while (l <= r && array[r] > pv) r--;
            if (l <= r) swap(array, l++, r--);
        }
        swap(array, left, r);
        return r;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
