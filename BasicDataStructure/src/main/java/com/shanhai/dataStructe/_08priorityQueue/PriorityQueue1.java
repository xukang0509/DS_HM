package com.shanhai.dataStructe._08priorityQueue;

import com.shanhai.dataStructe._05queue.Queue;

/**
 * 基于<b>无序数组</b>实现
 *
 * @param <E> 队列中元素类型, 必须实现 Priority 接口
 */
@SuppressWarnings("all")
public class PriorityQueue1<E extends Priority> implements Queue<E> {
    private Priority[] array;
    private int size;

    public PriorityQueue1(int capacity) {
        array = new Priority[capacity];
    }

    @Override // O(1)
    public boolean offer(E value) {
        if (isFull()) return false;
        array[size++] = value;
        return true;
    }

    @Override // O(n)
    public E poll() {
        if (isEmpty()) return null;
        int maxIdx = selectMax();
        E val = (E) array[maxIdx];
        remove(maxIdx);
        return val;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        int maxIdx = selectMax();
        return (E) array[maxIdx];
    }

    private void remove(int index) {
        if (index < array.length - 1) {
            // 移动
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
        array[--size] = null; // help GC
    }

    // 返回优先级最高的索引值
    private int selectMax() {
        int max = 0;
        for (int i = 0; i < size; i++) {
            if (array[max].priority() < array[i].priority()) {
                max = i;
            }
        }
        return max;
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

    public Priority[] getArray() {
        return array;
    }
}
