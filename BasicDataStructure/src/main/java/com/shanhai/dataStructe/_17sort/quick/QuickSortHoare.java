package com.shanhai.dataStructe._17sort.quick;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSortHoare<T extends Comparable<T>> extends BaseSort<T> {

    public QuickSortHoare(T[] array) {
        super(array);
    }

    public QuickSortHoare(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        quickSortHoare(0, array.length - 1);
    }

    private void quickSortHoare(int left, int right) {
        if (left >= right) return;
        // partition 表示基准点元素索引
        int partition = partition(left, right);
        quickSortHoare(left, partition - 1);
        quickSortHoare(partition + 1, right);
    }

    private int partition(int left, int right) {
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(idx, left);
        T pv = array[left]; // 基准元素值
        int i = left, j = right;
        while (i < j) {
            while (i < j && compare(array[j], pv) > 0) j--;
            while (i < j && compare(array[i], pv) <= 0) i++;
            swap(i, j);
        }
        swap(i, left);
        return i;
    }
}
