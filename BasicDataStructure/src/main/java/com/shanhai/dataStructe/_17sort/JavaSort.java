package com.shanhai.dataStructe._17sort;

import java.util.Arrays;
import java.util.Comparator;

public class JavaSort<T extends Comparable<T>> extends BaseSort<T> {
    public JavaSort(T[] array) {
        super(array);
    }

    public JavaSort(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        Arrays.sort(array);
    }
}
