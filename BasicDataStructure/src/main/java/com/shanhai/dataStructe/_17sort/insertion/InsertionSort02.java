package com.shanhai.dataStructe._17sort.insertion;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;

public class InsertionSort02<T extends Comparable<T>> extends BaseSort<T> {

    public InsertionSort02(T[] array) {
        super(array);
    }

    public InsertionSort02(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        insertionSort();
    }

    private void insertionSort() {
        for (int low = 1; low < array.length; low++) {
            // 将 low 位置的元素插入至 [0..low-1] 的已排序区域
            T t = array[low];
            int i; // 已排序区域指针
            for (i = low - 1; i >= 0 && compare(array[i], t) > 0; i--) { // 没有找到插入位置
                array[i + 1] = array[i]; // 空出插入位置
            }
            // 找到插入位置
            if (i != low - 1) {
                array[i + 1] = t;
            }
        }
    }
}
