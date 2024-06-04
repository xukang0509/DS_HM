package com.shanhai.dataStructe._07deque;

import java.util.Iterator;

/**
 * 基于循环数组实现, 特点
 * <ul>
 *     <li>容量是2的幂</li>
 *     <li>tail 停下来的位置不存储, 会浪费一个位置</li>
 * </ul>
 *
 * @param <E> 元素类型
 */
public class ArrayDeque2<E> implements Deque<E>, Iterable<E> {
    private final E[] array;
    private final int capacity;
    private int head;
    private int tail;

    public ArrayDeque2(int capacity) {
        if ((capacity & (capacity - 1)) != 0) {
            throw new IllegalArgumentException("capacity 必须是2的幂");
        }
        this.capacity = capacity;
        array = (E[]) new Object[this.capacity];
        head = tail = 0;
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) return false;
        array[--head & capacity - 1] = e;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) return false;
        array[tail++ & capacity - 1] = e;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) return null;
        int idx = head++ & capacity - 1;
        E e = array[idx];
        array[idx] = null; // help GC
        return e;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) return null;
        int idx = --tail & capacity - 1;
        E e = array[idx];
        array[idx] = null; // help GC
        return e;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) return null;
        return array[head & capacity - 1];
    }

    @Override
    public E peekLast() {
        if (isEmpty()) return null;
        return array[tail - 1 & capacity - 1];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head == capacity - 1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                return array[p++ & capacity - 1];
            }
        };
    }
}
