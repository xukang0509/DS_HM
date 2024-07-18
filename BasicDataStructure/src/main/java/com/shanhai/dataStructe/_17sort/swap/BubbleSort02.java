package com.shanhai.dataStructe._17sort.swap;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;

/**
 * @author xk
 */
public class BubbleSort02<T extends Comparable<T>> extends BaseSort<T> {
    public BubbleSort02(T[] array) {
        super(array);
    }

    public BubbleSort02(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    public void sort() {
        bubbleSort();
    }

    private void bubbleSort() {
        for (int j = array.length - 1; j > 0; ) {
            int x = 0;
            for (int i = 0; i < j; i++) {
                if (compare(i, i + 1) > 0) {
                    x = i;
                    swap(i, i + 1);
                }
            }
            j = x;
        }
    }
}