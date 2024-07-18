package com.shanhai.dataStructe._17sort.merge;

import com.shanhai.dataStructe._17sort.BaseSort;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort02<T extends Comparable<T>> extends BaseSort<T> {
    public MergeSort02(T[] array) {
        super(array);
    }

    public MergeSort02(T[] array, Comparator<T> comparator) {
        super(array, comparator);
    }

    @Override
    protected void sort() {
        mergeSort();
    }

    private void mergeSort() {
        int len = array.length;
        // width 代表有序区间的宽度，取值依次为 1、2、4 ...
        for (int width = 1; width < len; width *= 2) {
            // [left, right) 分别代表待合并区间的左右边界
            for (int left = 0; left < len; left += width * 2) {
                int right = Math.min(left + width * 2, len);
                int mid = Math.min(left + width, len);
                merge(left, mid, right);
            }
        }
    }

    private void merge(int begin, int mid, int end) {
        T[] temp = Arrays.copyOfRange(array, begin, mid);
        int ls = 0, le = mid - begin;
        int rs = mid, re = end;
        int k = begin;
        while (ls < le) {
            if (rs < re && compare(temp[ls], array[rs]) > 0) {
                array[k++] = array[rs++];
            } else {
                array[k++] = temp[ls++];
            }
        }
    }
}
