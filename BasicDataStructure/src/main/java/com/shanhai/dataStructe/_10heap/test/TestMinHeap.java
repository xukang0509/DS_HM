package com.shanhai.dataStructe._10heap.test;

import com.shanhai.dataStructe._10heap.MinHeap;

import java.util.Arrays;

public class TestMinHeap {
    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        MinHeap<Integer> heap = new MinHeap<>(array);
        System.out.println(Arrays.toString(heap.array));

        System.out.println("heap.peek() = " + heap.peek());

        System.out.println("heap.poll() = " + heap.poll());
        System.out.println(Arrays.toString(heap.array));

        System.out.println("heap.poll() = " + heap.poll());
        System.out.println(Arrays.toString(heap.array));
    }
}
