package com.shanhai.dataStructe._17sort.insertion;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ShellSort02<T extends Comparable<T>> extends BaseSort<T> {
    public ShellSort02(T[] array) {
        super(array);
    }

    public ShellSort02(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        shellSort();
    }

    private void shellSort() {
        List<Integer> sequence = shellStepSequence();
        for (Integer gap : sequence) {
            shellSort(gap);
        }
    }

    private void shellSort(Integer gap) {
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

    private List<Integer> shellStepSequence() {
        List<Integer> stepSequence = new LinkedList<>();
        int k = 0, step = 0;
        while (true) {
            if (k % 2 == 0) { // even
                int pow = (int) Math.pow(2, k >> 1);
                step = 1 + 9 * (pow * pow - pow);
            } else { // odd
                int pow1 = (int) Math.pow(2, (k - 1) >> 1);
                int pow2 = (int) Math.pow(2, (k + 1) >> 1);
                step = 1 + 8 * pow1 * pow2 - 6 * pow2;
            }
            if (step >= array.length) break;
            stepSequence.add(0, step);
            k++;
        }
        return stepSequence;
    }
}
