package com.shanhai.dataStructe._17sort.insertion;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;

public class InsertionSort03<T extends Comparable<T>> extends BaseSort<T> {

    public InsertionSort03(T[] array) {
        super(array);
    }

    public InsertionSort03(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        insertionSort();
    }

    private void insertionSort() {
        for (int i = 1; i < array.length; i++) {
            insert(i, findInsertion(i));
        }
    }

    private void insert(int source, int dest) {
        T t = array[source];
        for (int i = source - 1; i >= dest; i--) {
            array[i + 1] = array[i];
        }
        array[dest] = t;
    }

    private int findInsertion(int i) {
        int l = 0, r = i;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (compare(mid, i) > 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
