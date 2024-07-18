package com.shanhai.dataStructe._17sort;

import java.util.Comparator;

/*
 * 比较次数，交换次数
 * */
public abstract class BaseSort<T extends Comparable<T>> {
    protected T[] array;
    private long cmpCount;
    private long swapCount;

    private Comparator<T> comparator;

    public BaseSort(T[] array) {
        this.array = array;
    }

    public BaseSort(T[] array, Comparator<T> comparator) {
        this.array = array;
        this.comparator = comparator;
    }

    public SortedResult executeSort() {
        cmpCount = swapCount = 0;
        final long begin = System.currentTimeMillis();
        sort();
        final long end = System.currentTimeMillis();
        SortedResult sortedResult = new SortedResult(this.getClass().getSimpleName(), cmpCount,
                swapCount, end - begin);
        checkIsSorted();
        return sortedResult;
    }

    protected abstract void sort();

    protected void swap(int i, int j) {
        T t = array[i];
        array[i] = array[j];
        array[j] = t;
        swapCount++;
    }

    protected int compare(int i, int j) {
        return compare(array[i], array[j]);
    }

    protected int compare(T t1, T t2) {
        cmpCount++;
        return this.comparator != null ? comparator.compare(t1, t2) : t1.compareTo(t2);
    }

    private void checkIsSorted() {
        for (int i = 1; i < array.length; i++) {
            if (compare(i - 1, i) > 0) {
                throw new RuntimeException("Sorted method " + this.getClass().getSimpleName() + " is error");
            }
        }
    }
}
