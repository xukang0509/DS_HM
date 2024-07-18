package com.shanhai.dataStructe._17sort.selection;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;

/**
 * @author xk
 */
public class SelectionSort<T extends Comparable<T>> extends BaseSort<T> {
    public SelectionSort(T[] array) {
        super(array);
    }

    public SelectionSort(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        selectionSort();
    }

    private void selectionSort() {
        // 1. 选择轮数 a.length - 1
        // 2. 交换的索引位置(j) 初始 a.length - 1, 每次递减
        for (int j = array.length - 1; j > 0; j--) {
            int maxIndex = j;
            for (int i = 0; i < j; i++) {
                if (compare(i, maxIndex) > 0) {
                    maxIndex = i;
                }
            }
            if (maxIndex != j) swap(maxIndex, j);
        }
    }
}