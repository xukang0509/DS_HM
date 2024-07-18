package com.shanhai.dataStructe._17sort.radix;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;

public class RadixSort01 extends BaseSort<Integer> {
    private Integer[] newArray;

    public RadixSort01(Integer[] array) {
        super(array);
    }

    public RadixSort01(Integer[] array, Comparator<Integer> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        radixSort();
    }

    private void radixSort() {
        newArray = new Integer[array.length];
        Integer max = array[0];
        for (Integer num : array) {
            if (num > max) max = num;
        }
        for (int divider = 1; divider <= max; divider *= 10) {
            countingSort(divider);
        }
    }

    private void countingSort(int divider) {
        int[] countings = new int[10];
        for (Integer num : array) {
            countings[num / divider % 10]++;
        }
        for (int i = 1; i < countings.length; i++) {
            countings[i] += countings[i - 1];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            int index = --countings[array[i] / divider % 10];
            newArray[index] = array[i];
        }
        System.arraycopy(newArray, 0, array, 0, newArray.length);
    }
}
