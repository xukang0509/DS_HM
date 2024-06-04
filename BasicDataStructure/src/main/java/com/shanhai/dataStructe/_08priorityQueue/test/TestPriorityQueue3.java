package com.shanhai.dataStructe._08priorityQueue.test;

import com.shanhai.dataStructe._08priorityQueue.Entry;
import com.shanhai.dataStructe._08priorityQueue.Priority;
import com.shanhai.dataStructe._08priorityQueue.PriorityQueue3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPriorityQueue3 {

    PriorityQueue3<Priority> getQueue() {
        PriorityQueue3<Priority> queue = new PriorityQueue3<>(10);
        queue.setArray(new Priority[]{
                new Entry(100),
                new Entry(19),
                new Entry(36),
                new Entry(17),
                new Entry(3),
                new Entry(15),
                new Entry(1),
                new Entry(2),
                new Entry(16),
                null
        });
        queue.setSize(9);
        return queue;
    }

    @Test
    public void test() {
        PriorityQueue3<Priority> queue = getQueue();
        queue.poll();
        Assertions.assertArrayEquals(new Entry[]{
                new Entry(36),
                new Entry(19),
                new Entry(16),
                new Entry(17),
                new Entry(3),
                new Entry(15),
                new Entry(1),
                new Entry(2),
                null, null
        }, queue.getArray());

        queue.poll();
        Assertions.assertArrayEquals(new Entry[]{
                new Entry(19),
                new Entry(17),
                new Entry(16),
                new Entry(2),
                new Entry(3),
                new Entry(15),
                new Entry(1),
                null, null, null
        }, queue.getArray());
    }
}