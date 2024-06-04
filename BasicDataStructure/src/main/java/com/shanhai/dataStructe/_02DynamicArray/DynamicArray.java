package com.shanhai.dataStructe._02DynamicArray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @description:
 * @author: xu
 * @date: 2024/5/12 23:28
 */
@SuppressWarnings("unchecked")
public class DynamicArray<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    private int size;
    private Object[] elements;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int capacity) {
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public void clear() {
        // 内存管理
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E ele) {
        return indexOf(ele) != ELEMENT_NOT_FOUND;
    }

    public void add(E ele) {
        add(size, ele);
    }

    public void add(int index, E ele) {
        rangeCheckedForAdd(index);
        ensureCapacity(size + 1);
        if (index < size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = ele;
        size++;
    }

    public void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elements = Arrays.copyOf(elements, newCapacity);
    }


    public E get(int index) {
        rangeChecked(index);
        return element(index);
    }

    public E set(int index, E ele) {
        rangeChecked(index);
        E oldValue = element(index);
        elements[index] = ele;
        return oldValue;
    }

    public E remove(int index) {
        rangeChecked(index);
        E deletedVal = element(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null; // 内存管理
        return deletedVal;
    }

    public int indexOf(E ele) {
        if (ele == null) {
            for (int index = 0; index < size; index++) {
                if (element(index) == null) return index;
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (Objects.equals(ele, element(index))) return index;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    public E element(int index) {
        return (E) elements[index];
    }

    // ==================遍历方式==========================
    // 方式一：普通方法--函数式接口
    public void foreach(Consumer<E> consumer) {
        for (int i = 0; i < size; i++) {
            E element = element(i);
            consumer.accept(element);
        }
    }

    // 方式二：迭代器遍历
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() { // 返回当前元素，并移动到下一个元素
                return element(index++);
            }
        };
    }

    public Stream<E> stream() {
        return Stream.of(Arrays.copyOf((E[]) elements, size));
    }

    /*==============边界检查=====================*/
    private void rangeChecked(int index) {
        if (index < 0 || index >= size) {
            outOfBound(index);
        }
    }

    private void rangeCheckedForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBound(index);
        }
    }

    private void outOfBound(int index) {
        throw new RuntimeException("Out of Bound：Index = " + index + ", Size = " + size);
    }

    @Override
    public String toString() {
        return "DynamicArray{" +
                "size=" + size +
                ", elements=" + Arrays.toString(Arrays.copyOf(elements, size)) +
                '}';
    }
}
