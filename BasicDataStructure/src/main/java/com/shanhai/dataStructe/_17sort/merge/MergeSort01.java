package com.shanhai.dataStructe._17sort.merge;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort01<T extends Comparable<T>> extends BaseSort<T> {

    public MergeSort01(T[] array) {
        super(array);
    }

    public MergeSort01(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        mergeSort();
    }

    private void mergeSort() {
        split(0, array.length);
    }

    private void split(int begin, int end) {
        if (end - begin < 2) return;
        int mid = (begin + end) >>> 1;
        split(begin, mid);
        split(mid, end);
        merge(begin, mid, end);
    }

    private void merge(int begin, int mid, int end) {
        // 拷贝左边数组
        T[] t = Arrays.copyOfRange(array, begin, mid);
        int ls = 0, le = mid - begin;
        int rs = mid, re = end;
        int k = begin;
        while (ls < le) {
            if (rs < re && compare(t[ls], array[rs]) > 0) {
                array[k++] = array[rs++];
            } else {
                array[k++] = t[ls++];
            }
        }
    }
}
