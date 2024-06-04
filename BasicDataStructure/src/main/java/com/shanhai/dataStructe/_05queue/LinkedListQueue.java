package com.shanhai.dataStructe._05queue;

import java.util.Iterator;

public class LinkedListQueue<E> implements Queue<E>, Iterable<E> {
    private Node<E> head;
    private Node<E> tail;

    private int size;

    private int capacity = Integer.MAX_VALUE;

    public LinkedListQueue() {
        head = new Node<>(null, null);
        tail = head;
        tail.next = head;
        size = 0;
    }

    public LinkedListQueue(int capacity) {
        this();
        this.capacity = capacity;
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) return false;
        Node<E> added = new Node<>(value, tail.next);
        tail.next = added;
        tail = added;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        Node<E> first = head.next;
        head.next = first.next;
        if (first == tail) tail = head;
        size--;
        return first.value;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
        //return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != head;
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
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
