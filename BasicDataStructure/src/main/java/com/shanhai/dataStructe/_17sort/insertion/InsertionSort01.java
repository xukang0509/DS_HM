package com.shanhai.dataStructe._17sort.insertion;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;

public class InsertionSort01<T extends Comparable<T>> extends BaseSort<T> {

    public InsertionSort01(T[] array) {
        super(array);
    }

    public InsertionSort01(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        insertionSort();
    }

    private void insertionSort() {
        for (int i = 1; i < array.length; i++) {
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (compare(j, j + 1) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }
}
