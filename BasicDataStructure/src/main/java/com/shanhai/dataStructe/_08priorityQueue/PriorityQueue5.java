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
public class PriorityQueue5<E extends Comparable> implements Queue<E> {
    private Comparable[] array;
    private int size;

    public PriorityQueue5(int capacity) {
        array = new Comparable[capacity];
    }

    /*
    1. 入堆新元素, 加入到数组末尾 (索引位置 child)
    2. 不断比较新加元素与它父节点(parent)优先级 (上浮)
        - 如果父节点优先级低, 则向下移动, 并找到下一个 parent
        - 直至父节点优先级更高或 child==0 为止
     */
    @Override
    public boolean offer(E value) {
        if (isFull()) return false;
        int child = size++;
        int parent = (child - 1) >>> 1;
        while (child > 0 && value.compareTo(array[parent]) > 0) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) >>> 1;
        }
        array[child] = value;
        return true;
    }

    /*
    1. 交换堆顶和尾部元素, 让尾部元素出队
    2. (下潜)
        - 从堆顶开始, 将父元素与两个孩子较大者交换
        - 直到父元素大于两个孩子, 或没有孩子为止
     */
    @Override
    public E poll() {
        if (isEmpty()) return null;
        swap(0, --size);
        E e = (E) array[size];
        array[size] = null; // help GC
        down(0);
        return e;
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
        E temp = (E) array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /*
        (下潜)
        - 从堆顶开始, 将父元素与两个孩子较大者交换
        - 直到父元素大于两个孩子, 或没有孩子为止
     */
    private void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int max = parent;
        // 两个孩子里找较大的
        if (left < size && array[left].compareTo(array[max]) > 0) {
            max = left;
        }
        if (right < size && array[right].compareTo(array[max]) > 0) {
            max = right;
        }
        if (max != parent) { // 有孩子比父亲大
            swap(parent, max);
            down(max);
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
