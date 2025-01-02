package com.shanhai.algorithm.monotoneStack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author xk
 * @since 2024-08-20 9:05
 */
public class MonotonicQueue<T extends Comparable<T>> {

    private final Deque<T> queue = new LinkedList<>();

    public static void main(String[] args) {
        MonotonicQueue<Integer> stack = new MonotonicQueue<>();
        for (Integer i : new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}) {
            stack.offer(i);
            System.out.println(stack);
        }
    }

    public void offer(T t) {
        while (!queue.isEmpty() && queue.peekLast().compareTo(t) < 0) {
            queue.pollLast();
        }
        queue.offerLast(t);
    }

    public T poll() {
        if (queue.isEmpty()) return null;
        return queue.pollFirst();
    }

    public T peek() {
        if (queue.isEmpty()) return null;
        return queue.peekFirst();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
