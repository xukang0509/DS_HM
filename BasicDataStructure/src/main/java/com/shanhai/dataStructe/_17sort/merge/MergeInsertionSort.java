package com.shanhai.dataStructe._17sort.merge;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Arrays;
import java.util.Comparator;

public class MergeInsertionSort<T extends Comparable<T>> extends BaseSort<T> {
    public MergeInsertionSort(T[] array) {
        super(array);
    }

    public MergeInsertionSort(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        mergeInsertionSort();
    }

    private void mergeInsertionSort() {
        split(0, array.length);
    }

    private void split(int begin, int end) {
        if (end - begin < 2) return;
        if (end - begin <= 32) {
            insertionSort(begin, end);
            return;
        }
        int mid = (begin + end) >>> 1;
        split(begin, mid);
        split(mid, end);
        merge(begin, mid, end);
    }

    private void merge(int begin, int mid, int end) {
        // 拷贝左边数组
        T[] temp = Arrays.copyOfRange(array, begin, mid);
        int ls = 0, le = mid - begin;
        int rs = mid, re = end;
        int k = begin;
        while (ls < le) {
            if (rs < re && compare(temp[ls], array[rs]) > 0) {
                array[k++] = array[rs++];
            } else {
                array[k++] = temp[ls++];
            }
        }
    }

    private void insertionSort(int begin, int end) {
        for (int i = begin + 1; i < end; i++) {
            T t = array[i];
            int j;
            for (j = i - 1; j >= begin && compare(array[j], t) > 0; j--) {
                array[j + 1] = array[j];
            }
            if (j != i - 1) {
                array[j + 1] = t;
            }
        }
    }
}
