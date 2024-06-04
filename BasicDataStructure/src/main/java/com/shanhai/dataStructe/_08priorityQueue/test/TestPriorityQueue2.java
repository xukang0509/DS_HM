package com.shanhai.dataStructe._08priorityQueue.test;

import com.shanhai.dataStructe._08priorityQueue.Entry;
import com.shanhai.dataStructe._08priorityQueue.PriorityQueue2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestPriorityQueue2 {

    @Test
    public void poll() {
        PriorityQueue2<Entry> queue = new PriorityQueue2<>(5);
        queue.offer(new Entry("task1", 4));
        queue.offer(new Entry("task2", 3));
        queue.offer(new Entry("task3", 2));
        queue.offer(new Entry("task4", 5));
        queue.offer(new Entry("task5", 1));
        assertFalse(queue.offer(new Entry("task6", 7)));

        assertEquals("task4", queue.peek().getValue());
        assertEquals("task4", queue.poll().getValue());
        assertEquals("task1", queue.poll().getValue());
        assertEquals("task2", queue.poll().getValue());
        assertEquals("task3", queue.poll().getValue());
        assertEquals("task5", queue.poll().getValue());
    }
}
