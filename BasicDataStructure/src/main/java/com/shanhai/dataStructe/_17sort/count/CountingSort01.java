package com.shanhai.dataStructe._17sort.count;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;

public class CountingSort01 extends BaseSort<Integer> {
    public CountingSort01(Integer[] array) {
        super(array);
    }

    public CountingSort01(Integer[] array, Comparator<Integer> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        countingSort();
    }

    private void countingSort() {
        // 找出最大最小值
        int min = array[0];
        int max = array[0];
        for (int num : array) {
            if (num < min) {
                min = num;
            } else if (num > max) {
                max = num;
            }
        }
        // 开辟内存空间，存储次数
        int[] counts = new int[max - min + 1];
        // 统计每个整数出现的次数
        for (int num : array) {
            counts[num - min]++;
        }
        // 根据整数的出现次数，对整数进行排序
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            // (i + min) 代表原始数组元素，counts[i] 代表元素出现个数
            while (counts[i] > 0) {
                array[index++] = i + min;
                counts[i]--;
            }
        }
    }
}
