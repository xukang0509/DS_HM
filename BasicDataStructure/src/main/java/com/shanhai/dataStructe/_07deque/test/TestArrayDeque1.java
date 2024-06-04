package com.shanhai.dataStructe._07deque.test;

import com.shanhai.dataStructe._07deque.ArrayDeque1;
import org.junit.jupiter.api.Test;


public class TestArrayDeque1 {
    @Test
    public void test03() {
        ArrayDeque1<Integer> deque = new ArrayDeque1<>(3);
        // 2 1 3
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerLast(3);

        System.out.println("deque.offerLast(4) = " + deque.offerLast(4));
        deque.forEach(System.out::println);
    }

    @Test
    public void test04() {
        ArrayDeque1<Integer> deque = new ArrayDeque1<>(7);
        System.out.println("deque.isEmpty() = " + deque.isEmpty());
        deque.offerLast(1);
        deque.offerLast(2);
        deque.offerLast(3);
        deque.offerFirst(4);
        deque.offerFirst(5);
        deque.offerFirst(6);
        deque.offerFirst(7);
        System.out.println("===========================");
        deque.forEach(System.out::println);
        System.out.println("===========================");
        System.out.println("deque.isFull() = " + deque.isFull());

        System.out.println("deque.pollFirst() = " + deque.pollFirst());
        System.out.println("deque.pollFirst() = " + deque.pollFirst());
        System.out.println("deque.pollLast() = " + deque.pollLast());
        System.out.println("deque.pollLast() = " + deque.pollLast());
        System.out.println("deque.pollLast() = " + deque.pollLast());
        System.out.println("deque.pollLast() = " + deque.pollLast());
        System.out.println("deque.pollLast() = " + deque.pollLast());
        System.out.println("deque.pollLast() = " + deque.pollLast());

        System.out.println("deque.isEmpty() = " + deque.isEmpty());
    }

    @Test
    public void test05() {
        ArrayDeque1<Integer> deque = new ArrayDeque1<>(7);
        // 3 1 2 4
        deque.offerFirst(1);
        deque.offerLast(2);
        deque.offerFirst(3);
        deque.offerLast(4);

        System.out.println("deque.peekLast() = " + deque.peekLast());
        System.out.println("deque.peekFirst() = " + deque.peekFirst());
    }
}
