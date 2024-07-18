package com.shanhai.dataStructe._17sort.count;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Comparator;

public class CountingSort03 extends BaseSort<Integer> {

    public CountingSort03(Integer[] array) {
        super(array);
    }

    public CountingSort03(Integer[] array, Comparator<Integer> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        countingSort();
    }

    private void countingSort() {
        // 找出最大最小值
        int min = array[0], max = array[0];
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
        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        // 从后往前遍历元素，将它放到有序数组中的合适位置
        Integer[] temp = new Integer[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int index = --counts[array[i] - min];
            temp[index] = array[i];
        }
        // 将有序数组赋值到array
        System.arraycopy(temp, 0, array, 0, temp.length);
    }
}
