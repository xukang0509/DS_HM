package com.shanhai.dataStructe._06stack;

import java.util.Iterator;

public class ArrayStack<E> implements Stack<E>, Iterable<E> {
    private final E[] array;
    private int top;

    public ArrayStack(int capacity) {
        this.array = (E[]) new Object[capacity];
        this.top = 0;
    }

    @Override
    public boolean push(E value) {
        if (isFull()) return false;
        array[top++] = value;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) return null;
        E e = array[--top];
        array[top] = null;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return array[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = top;

            @Override
            public boolean hasNext() {
                return p > 0;
            }

            @Override
            public E next() {
                return array[--p];
            }
        };
    }
}
