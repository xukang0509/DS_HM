package com.shanhai.dataStructe._08priorityQueue.test;

import com.shanhai.dataStructe._08priorityQueue.PriorityQueue5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPriorityQueue5 {

    PriorityQueue5<Integer> getQueue() {
        PriorityQueue5<Integer> queue = new PriorityQueue5<>(10);
        queue.setArray(new Integer[]{100, 19, 36, 17, 3, 15, 1, 2, 16, null});
        queue.setSize(9);
        return queue;
    }

    @Test
    public void test() {
        PriorityQueue5<Integer> queue = getQueue();
        queue.poll();
        Assertions.assertArrayEquals(new Integer[]{36, 19, 16, 17, 3, 15, 1, 2, null, null}, queue.getArray());

        queue.poll();
        Assertions.assertArrayEquals(new Integer[]{19, 17, 16, 2, 3, 15, 1, null, null, null}, queue.getArray());
    }
}