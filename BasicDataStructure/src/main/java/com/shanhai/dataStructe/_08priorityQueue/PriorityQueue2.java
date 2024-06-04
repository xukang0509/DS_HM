package com.shanhai.dataStructe._08priorityQueue;

import com.shanhai.dataStructe._05queue.Queue;

/**
 * 基于<b>无序数组</b>实现
 *
 * @param <E> 队列中元素类型, 必须实现 Priority 接口
 */
@SuppressWarnings("all")
public class PriorityQueue2<E extends Priority> implements Queue<E> {
    private Priority[] array;
    private int size;

    public PriorityQueue2(int capacity) {
        array = new Priority[capacity];
    }

    @Override // O(1)
    public boolean offer(E value) {
        if (isFull()) return false;
        insert(value);
        size++;
        return true;
    }

    @Override // O(1)
    public E poll() {
        if (isEmpty()) return null;
        E e = (E) array[--size];
        array[size] = null; // help GC
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return (E) array[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public int size() {
        return size;
    }

    // O(n)
    private void insert(E value) {
        int i = size - 1;
        while (i >= 0 && array[i].priority() > value.priority()) {
            array[i + 1] = array[i];
            i--;
        }
        array[i + 1] = value;
    }

    public Priority[] getArray() {
        return array;
    }
}
