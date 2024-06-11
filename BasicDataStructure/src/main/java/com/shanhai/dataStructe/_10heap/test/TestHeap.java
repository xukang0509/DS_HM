package com.shanhai.dataStructe._10heap.test;

import com.shanhai.dataStructe._10heap.Heap;
import org.junit.jupiter.api.Test;

public class TestHeap {
    @Test
    public void test01() {
        Heap<Integer> maxHeap = new Heap<>(10);
        for (int i = 0; i < 8; i++) {
            maxHeap.offer(i + 1);
        }
        System.out.println(maxHeap);

        maxHeap.offer(9);
        maxHeap.offer(10);
        maxHeap.offer(11);

        System.out.println(maxHeap);
    }

    @Test
    public void test02() {
        Heap<Integer> minHeap = new Heap<>(10, (a, b) -> Integer.compare(b, a));
        for (int i = 11; i > 0; i--) {
            minHeap.offer(i);
        }
        System.out.println(minHeap);

        System.out.println("minHeap.peek() = " + minHeap.peek());

        System.out.println("minHeap.poll() = " + minHeap.poll());
        System.out.println(minHeap);
        System.out.println("minHeap.poll() = " + minHeap.poll());
        System.out.println(minHeap);
        System.out.println("minHeap.poll() = " + minHeap.poll());
        System.out.println(minHeap);
        System.out.println("minHeap.poll() = " + minHeap.poll());
        System.out.println(minHeap);
    }
}
