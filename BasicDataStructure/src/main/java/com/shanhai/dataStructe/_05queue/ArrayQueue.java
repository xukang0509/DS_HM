package com.shanhai.dataStructe._05queue;

import java.util.Iterator;

public class ArrayQueue<E> implements Queue<E>, Iterable<E> {
    private final int length;
    private final E[] array;
    private int head;
    private int tail;

    public ArrayQueue(int capacity) {
        this.length = capacity + 1;
        array = (E[]) new Object[length];
        head = tail = 0;
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) return false;
        array[tail] = value;
        tail = (tail + 1) % length;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        E value = array[head];
        head = (head + 1) % length;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return head == ((tail + 1) % length);
    }

    @Override
    public int size() {
        return (tail - head + length) % length;
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
                E value = array[index];
                index = (index + 1) % length;
                return value;
            }
        };
    }
}
