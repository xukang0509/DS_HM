package com.shanhai.dataStructe._07deque;

import java.util.Iterator;

/**
 * 基于环形链表的双端队列
 *
 * @param <E> 元素类型
 */
public class LinkedListDeque<E> implements Deque<E>, Iterable<E> {
    private final int capacity;
    private Node<E> sentinel;
    private int size;

    public LinkedListDeque(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.sentinel = new Node<>(null, null, null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) return false;
        Node<E> added = new Node<>(sentinel, e, sentinel.next);
        sentinel.next = added;
        added.next.prev = added;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) return false;
        Node<E> added = new Node<>(sentinel.prev, e, sentinel);
        sentinel.prev = added;
        added.prev.next = added;
        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) return null;
        Node<E> delNode = sentinel.next;
        Node<E> next = delNode.next;
        sentinel.next = next;
        next.prev = sentinel;
        size--;
        return delNode.value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) return null;
        Node<E> delNode = sentinel.prev;
        Node<E> prev = delNode.prev;
        sentinel.prev = prev;
        prev.next = sentinel;
        size--;
        return delNode.value;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) return null;
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if (isEmpty()) return null;
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        Node<E> prev;
        E value;
        Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
