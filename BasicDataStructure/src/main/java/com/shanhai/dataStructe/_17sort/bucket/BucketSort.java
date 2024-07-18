package com.shanhai.dataStructe._17sort.bucket;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BucketSort extends BaseSort<Integer> {
    public BucketSort(Integer[] array) {
        super(array);
    }

    public BucketSort(Integer[] array, Comparator<Integer> comparator) {
        super(array, comparator);
    }

    public static void main(String[] args) {
        BucketSort baseSort = new BucketSort(new Integer[]{20, 18, 66, 25, 67, 30});
        System.out.println(baseSort.executeSort());
    }

    @Override
    protected void sort() {
        bucketSort();
    }

    private void bucketSort() {
        int len = 10;
        List<Integer>[] buckets = new ArrayList[len];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (Integer num : array) {
            buckets[num / len].add(num);
        }
        for (List<Integer> bucket : buckets) {
            System.out.println(Arrays.toString(bucket.toArray()));
        }
        int k = 0;
        for (List<Integer> bucket : buckets) {
            Integer[] integers = bucket.toArray(new Integer[0]);
            countingSort(integers);
            for (Integer integer : integers) {
                array[k++] = integer;
            }
        }
    }

    private void countingSort(Integer[] arr) {
        if (arr.length <= 0) return;
        // 找出最大最小值
        int min = arr[0], max = arr[0];
        for (int num : arr) {
            if (num < min) {
                min = num;
            } else if (num > max) {
                max = num;
            }
        }
        // 开辟内存空间，存储次数
        int[] counts = new int[max - min + 1];
        // 统计每个整数出现的次数
        for (int num : arr) {
            counts[num - min]++;
        }
        // 累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        // 从后往前遍历元素，将它放到有序数组中的合适位置
        Integer[] temp = new Integer[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = --counts[arr[i] - min];
            temp[index] = arr[i];
        }
        // 将有序数组赋值到array
        System.arraycopy(temp, 0, arr, 0, temp.length);
    }
}
