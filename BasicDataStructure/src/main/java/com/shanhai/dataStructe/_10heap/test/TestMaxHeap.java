package com.shanhai.dataStructe._10heap.test;

import com.shanhai.dataStructe._10heap.MaxHeap;

import java.util.Arrays;

public class TestMaxHeap {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        MaxHeap<Integer> heap = new MaxHeap<>(array);
        System.out.println(Arrays.toString(heap.array));

        System.out.println("heap.peek() = " + heap.peek());

        System.out.println("heap.poll() = " + heap.poll());
        System.out.println(Arrays.toString(heap.array));

        System.out.println("heap.poll() = " + heap.poll());
        System.out.println(Arrays.toString(heap.array));


    }
}
