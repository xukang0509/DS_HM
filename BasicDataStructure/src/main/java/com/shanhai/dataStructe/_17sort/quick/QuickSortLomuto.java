package com.shanhai.dataStructe._17sort.quick;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSortLomuto<T extends Comparable<T>> extends BaseSort<T> {

    public QuickSortLomuto(T[] array) {
        super(array);
    }

    public QuickSortLomuto(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        quickSortLomuto(0, array.length - 1);
    }

    private void quickSortLomuto(int left, int right) {
        if (left >= right) return;
        // partition 表示基准点元素索引
        int partition = partition(left, right);
        quickSortLomuto(left, partition - 1);
        quickSortLomuto(partition + 1, right);
    }

    private int partition(int left, int right) {
        int idx = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(idx, right);
        T pv = array[right]; // 基准元素值
        int i = left, j = left;
        while (j < right) {
            if (compare(pv, array[j]) > 0) {
                if (i != j) swap(i, j);
                i++;
            }
            j++;
        }
        swap(right, i);
        return i;
    }
}
