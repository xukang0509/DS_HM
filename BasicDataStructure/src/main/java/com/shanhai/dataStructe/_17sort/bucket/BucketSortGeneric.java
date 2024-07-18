package com.shanhai.dataStructe._17sort.bucket;

import java.util.Arrays;
import java.util.LinkedList;

public class BucketSortGeneric {
    public static void main(String[] args) {
        int[] ages = {20, 10, 28, 66, 25, 31, 67, 30, 70}; // 假设人类年龄 1~99
        System.out.println(Arrays.toString(ages));
        sort(ages, 20);
        System.out.println(Arrays.toString(ages));
    }

    private static void sort(int[] array, int range) {
        int min = array[0];
        int max = array[0];
        for (int num : array) {
            if (num < min) min = num;
            else if (num > max) max = num;
        }
        // 1.准备桶
        LinkedList<Integer>[] buckets = new LinkedList[(max - min) / range + 1];
        System.out.println("buckets.length = " + buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        // 2.放入数据
        for (int num : array) {
            buckets[(num - min) / range].addLast(num);
        }
        int k = 0;
        for (LinkedList<Integer> bucket : buckets) {
            // 3.排序桶内元素
            Integer[] arr = bucket.toArray(new Integer[0]);
            countingSort(arr);
            System.out.println(Arrays.toString(arr));
            // 4.把每个桶排序好的内容，依次放入原始数组
            for (Integer i : arr) {
                array[k++] = i;
            }
        }
    }

    private static void countingSort(Integer[] arr) {
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
