package com.shanhai.dataStructe._17sort.insertion;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;

public class ShellSort01<T extends Comparable<T>> extends BaseSort<T> {
    public ShellSort01(T[] array) {
        super(array);
    }

    public ShellSort01(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        shellSort();
    }

    private void shellSort() {
        for (int gap = array.length >> 1; gap > 0; gap >>= 1) {
            for (int low = gap; low < array.length; low++) {
                T t = array[low];
                int i;
                for (i = low - gap; i >= 0 && compare(array[i], t) > 0; i -= gap) {
                    array[i + gap] = array[i];
                }
                if (i != low - gap) {
                    array[i + gap] = t;
                }
            }
        }
    }
}
