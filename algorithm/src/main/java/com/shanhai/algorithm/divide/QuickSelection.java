package com.shanhai.algorithm.divide;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xk
 * @since 2024-08-12 20:55
 */
public class QuickSelection {
    public static void main(String[] args) {
        int[] array = {3, 1, 6, 7, 4, 5, 9, 8};
        System.out.println(quickSelection(array, 0)); // 1
        System.out.println(quickSelection(array, 1)); // 3
        System.out.println(quickSelection(array, 2)); // 4
        System.out.println(quickSelection(array, 3)); // 5
        System.out.println(quickSelection(array, 4)); // 6
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
