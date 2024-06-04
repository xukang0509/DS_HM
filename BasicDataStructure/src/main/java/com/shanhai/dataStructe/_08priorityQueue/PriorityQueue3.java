package com.shanhai.dataStructe._08priorityQueue;

import com.shanhai.dataStructe._05queue.Queue;
import lombok.Data;

import java.util.Arrays;

/**
 * 基于<b>无序数组</b>实现
 *
 * @param <E> 队列中元素类型, 必须实现 Priority 接口
 */
@SuppressWarnings("all")
@Data
public class PriorityQueue3<E extends Priority> implements Queue<E> {
    private Priority[] array;
    private int size;

    public PriorityQueue3(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(Priority value) {
        if (isFull()) return false;
        int child = size++;
        int parent = (child - 1) >>> 1;
        while (child > 0 && value.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) >>> 1;
        }
        array[child] = value;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        swap(0, --size);
        Priority e = array[size];
        array[size] = null; // help GC
        down(0, array[0]);
        return (E) e;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return (E) array[0];
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

    private void swap(int i, int j) {
        Priority temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void down(int parent, Priority top) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int max = parent;
        // 两个孩子里找较大的
        if (left < size) {
            max = left;
            if (right < size && array[right].priority() > array[left].priority()) {
                max = right;
            }
        }
        if (max != parent && array[max].priority() > top.priority()) {
            array[parent] = array[max];
            down(max, top);
        } else {
            array[parent] = top;
        }
    }

    @Override
    public String toString() {
        return "PriorityQueue3{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }
}
