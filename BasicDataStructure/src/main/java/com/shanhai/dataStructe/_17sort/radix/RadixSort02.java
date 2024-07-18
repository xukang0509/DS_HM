package com.shanhai.dataStructe._17sort.radix;

import com.shanhai.dataStructe._17sort.BaseSort;
import com.shanhai.utils.Integers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class RadixSort02 extends BaseSort<Integer> {

    public RadixSort02(Integer[] array) {
        super(array);
    }

    public RadixSort02(Integer[] array, Comparator<Integer> comparator) {
        super(array, comparator);
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[]{126, 69, 593, 23, 6, 89, 54, 8};
        RadixSort02 radixSort02 = new RadixSort02(integers);
        System.out.println(Arrays.toString(integers));
        radixSort02.sort();
        System.out.println(Arrays.toString(integers));
        Integers.isAscOrder(integers);
    }

    @Override
    protected void sort() {
        radixSort();
    }

    private void radixSort() {
        Integer max = array[0]; // 最大值
        for (Integer num : array) {
            if (num > max) max = num;
        }
        // 桶
        LinkedList<Integer>[] buckets = new LinkedList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        for (int divider = 1; divider <= max; divider *= 10) {
            for (int i = 0; i < array.length; i++) {
                buckets[array[i] / divider % 10].addLast(array[i]);
            }
            int idx = 0;
            for (LinkedList<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    array[idx++] = bucket.removeFirst();
                }
            }
        }
    }
}
