package com.shanhai.dataStructe._03LinkedList;

import java.util.Iterator;

/**
 * @description: 双向链表
 * @author: xu
 * @date: 2024/5/16 10:12
 */
public class DoubleLinkedListSentinel<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;

    public DoubleLinkedListSentinel() {
        this.head = new Node<>(null, null, null);
        this.tail = new Node<>(null, null, null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }


    private Node<T> findNode(int index) {
        int i = -1;
        for (Node<T> p = head; p != tail; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    public void addFirst(T value) {
        insert(0, value);
    }

    public void removeFirst() {
        remove(0);
    }

    public void addLast(T value) {
        Node<T> prev = this.tail.prev;
        Node<T> next = this.tail;
        Node<T> newNode = new Node<>(prev, value, next);
        prev.next = newNode;
        next.prev = newNode;
    }

    public void removeLast() {
        Node<T> removed = this.tail.prev;
        if (removed == this.head) {
            throw illegalArgument(0);
        }
        Node<T> prev = removed.prev;
        prev.next = this.tail;
        this.tail.prev = prev;
    }

    public void insert(int index, T value) {
        Node<T> pre = findNode(index - 1);
        if (pre == null) {
            throw illegalArgument(index);
        }
        Node<T> next = pre.next;
        Node<T> newNode = new Node<>(pre, value, next);
        pre.next = newNode;
        next.prev = newNode;
    }

    public T remove(int index) {
        Node<T> pre = findNode(index - 1);
        if (pre == null) {
            throw illegalArgument(index);
        }
        Node<T> delNode = pre.next;
        if (delNode == tail) {
            throw illegalArgument(index);
        }
        T value = delNode.value;
        Node<T> next = delNode.next;
        pre.next = next;
        next.prev = pre;
        return value;
    }

    private IllegalArgumentException illegalArgument(int index) {
        return new IllegalArgumentException("非法的index：" + index);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public T next() {
                T value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        Node<T> prev;
        T value;
        Node<T> next;

        public Node(Node<T> prev, T value, Node<T> next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
