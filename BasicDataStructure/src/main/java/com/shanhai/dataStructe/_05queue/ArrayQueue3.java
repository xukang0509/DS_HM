package com.shanhai.dataStructe._05queue;

import java.util.Iterator;

public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {
    private final int capacity;
    private final E[] array;
    private int head;
    private int tail;

    @SuppressWarnings("all")
    public ArrayQueue3(int c) {
       /* if ((c & c - 1) != 0) {
            throw new IllegalArgumentException("c 必须为 2 的幂");
        }*/

        // 2. 改成 2^n    13 -> 16   22 -> 32
        c -= 1;
        c |= c >> 1;
        c |= c >> 2;
        c |= c >> 4;
        c |= c >> 8;
        c |= c >> 16;
        c += 1;
        this.capacity = c;
        array = (E[]) new Object[this.capacity];
        head = tail = 0;
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) return false;
        array[index(tail)] = value;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        E value = array[index(head)];
        head++;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return array[index(head)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head == capacity;
    }

    @Override
    public int size() {
        return tail - head;
    }

    private int index(int idx) {
        return idx & capacity - 1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int index = head;

            @Override
            public boolean hasNext() {
                return index != tail;
            }

            @Override
            public E next() {
                E value = array[index(index)];
                index++;
                return value;
            }
        };
    }
}
