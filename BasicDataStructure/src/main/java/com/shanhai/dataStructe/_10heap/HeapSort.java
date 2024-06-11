package com.shanhai.dataStructe._10heap;

import java.util.Arrays;
import java.util.random.RandomGenerator;

public class HeapSort {
    public static void main(String[] args) {
        int[] array = new int[30];
        for (int i = 0; i < array.length; i++) {
            array[i] = RandomGenerator.getDefault().nextInt(0, 100);
        }
        System.out.println("Before sorted: " + Arrays.toString(array));
        heapSort(array);
        System.out.println("After sorted: " + Arrays.toString(array));
    }

    private static void heapSort(int[] array) {
        heapify(array);
        int size = array.length;
        while (size > 0) {
            swap(array, 0, size - 1);
            size--;
            down(array, 0, size);
        }
    }

    // 建堆
    private static void heapify(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            down(array, i, array.length);
        }
    }

    // 下潜
    private static void down(int[] array, int parent, int size) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        if (left < size && array[left] > array[max]) {
            max = left;
        }
        if (right < size && array[right] > array[max]) {
            max = right;
        }
        if (max != parent) {
            swap(array, max, parent);
            down(array, max, size);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
