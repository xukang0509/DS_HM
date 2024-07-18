package com.shanhai.dataStructe._17sort.quick;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSortHandleDuplicate<T extends Comparable<T>> extends BaseSort<T> {

    public QuickSortHandleDuplicate(T[] array) {
        super(array);
    }

    public QuickSortHandleDuplicate(T[] array, Comparator<T> comparator) {
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
        int i = left + 1, j = right;
        while (i <= j) {
            // i 从左向右找大的或者相等的
            while (i <= j && compare(array[i], pv) < 0) i++;
            // j 从右向左找小的或者相等的
            while (i <= j && compare(array[j], pv) > 0) j--;
            if (i <= j) swap(i++, j--);
        }
        swap(left, j);
        return j;
    }
}
