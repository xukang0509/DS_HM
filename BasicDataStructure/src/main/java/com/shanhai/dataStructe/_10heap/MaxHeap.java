package com.shanhai.dataStructe._10heap;

public class MaxHeap<E extends Comparable> {
    public Comparable[] array;
    public int size;

    public MaxHeap(int capacity) {
        this.array = new Comparable[capacity];
    }

    public MaxHeap(E[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    public boolean offer(E e) {
        if (isFull()) return false;
        up(e);
        size++;
        return true;
    }

    // 将offered元素上浮：直到offered小于父元素或者到堆顶
    private void up(E offered) {
        int child = size;
        int parent = (child - 1) / 2;
        while (child > 0 && offered.compareTo(array[parent]) > 0) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = offered;
    }

    public E poll() {
        if (isEmpty()) return null;
        E top = (E) array[0];
        swap(0, --size);
        array[size] = null; // help GC
        down(0);
        return top;
    }

    public E peek() {
        if (isEmpty()) return null;
        return (E) array[0];
    }

    // 替换堆顶元素
    public void replace(E replaced) {
        array[0] = replaced;
        down(0);
    }

    // 建堆
    private void heapify() {
        // int i = array.length / 2 - 1 : 找到最后一个非叶子节点
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    // 将parent处的元素下潜：与两个孩子较大者交换，直到没有孩子比他大
    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        if ((left < size) && (array[left].compareTo(array[max]) > 0)) {
            max = left;
        }
        if ((right < size) && (array[right].compareTo(array[max]) > 0)) {
            max = right;
        }
        if (max != parent) { // 找到了更大的孩子
            swap(parent, max);
            down(max);
        }
    }

    // 交换两个索引的元素
    private void swap(int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }
}
