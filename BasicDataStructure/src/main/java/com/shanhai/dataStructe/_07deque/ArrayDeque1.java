package com.shanhai.dataStructe._07deque;

import java.util.Iterator;

public class ArrayDeque1<E> implements Deque<E>, Iterable<E> {
    private final E[] array;
    private final int capacity;
    private int head;
    private int tail;

    public ArrayDeque1(int capacity) {
        this.capacity = capacity + 1;
        array = (E[]) new Object[this.capacity];
        head = tail = 0;
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) return false;
        head = dec(head);
        array[head] = e;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) return false;
        array[tail] = e;
        tail = inc(tail);
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) return null;
        E e = array[head];
        array[head] = null;
        head = inc(head);
        return e;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) return null;
        tail = dec(tail);
        E e = array[tail];
        array[tail] = null;
        return e;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) return null;
        return array[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty()) return null;
        return array[dec(tail)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        if (tail > head) {
            return tail - head == capacity - 1;
        } else if (head > tail) {
            return head - tail == 1;
        } else {
            return false;
        }
    }


    private int inc(int i) {
        if (i + 1 >= capacity) return 0;
        return i + 1;
    }

    private int dec(int i) {
        if (i - 1 < 0) return capacity - 1;
        return i - 1;
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
                E e = array[p];
                p = inc(p);
                return e;
            }
        };
    }
}
