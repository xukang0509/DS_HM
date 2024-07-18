package com.shanhai.dataStructe._17sort.selection;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;

public class HeapSort<T extends Comparable<T>> extends BaseSort<T> {

    public HeapSort(T[] array) {
        super(array);
    }

    public HeapSort(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        heapSort();
    }

    private void heapSort() {
        heapify();
        for (int i = array.length - 1; i > 0; i--) {
            swap(0, i);
            down(0, i);
        }
    }

    // 构建大顶堆
    private void heapify() {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            down(i, array.length);
        }
    }

    // 下潜
    private void down(int parent, int length) {
        while (true) {
            int left = parent * 2 + 1;
            int right = left + 1;
            int max = parent;
            if (left < length && compare(left, max) > 0) {
                max = left;
            }
            if (right < length && compare(right, max) > 0) {
                max = right;
            }
            if (max == parent) break; // 没找到更大的孩子
            swap(max, parent);
            parent = max;
        }
    }
}
