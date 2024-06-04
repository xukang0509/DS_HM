package com.shanhai.dataStructe._03LinkedList;

import java.util.Iterator;
import java.util.Objects;

/**
 * 环形链表
 *
 * @param <T>
 */
public class DoublyLinkedListSentinel<T> implements Iterable<T> {

    private Node<T> sentinel;

    public DoublyLinkedListSentinel() {
        this.sentinel = new Node<>(null, null, null);
        this.sentinel.next = this.sentinel.prev = this.sentinel;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(sentinel, value, sentinel.next);
        newNode.next.prev = newNode;
        this.sentinel.next = newNode;
    }

    public void addLast(T value) {
        Node<T> newNode = new Node<>(sentinel.prev, value, sentinel);
        newNode.prev.next = newNode;
        sentinel.prev = newNode;
    }

    public void removeFirst() {
        Node<T> removed = sentinel.next;
        if (removed == sentinel) {
            throw new IllegalArgumentException();
        }
        Node<T> next = removed.next;
        Node<T> prev = removed.prev;
        prev.next = next;
        next.prev = prev;
    }

    public void removeLast() {
        Node<T> deleted = sentinel.prev;
        if (deleted == sentinel) {
            throw new IllegalArgumentException();
        }
        Node<T> last = deleted.prev;
        sentinel.prev = last;
        last.next = sentinel;
    }

    public void removeByValue(T value) {
        Node<T> deleted = findNodeByValue(value);
        if (deleted == null) {
            throw new IllegalArgumentException();
        }
        Node<T> prev = deleted.prev;
        Node<T> next = deleted.next;
        prev.next = next;
        next.prev = prev;
    }

    private Node<T> findNodeByValue(T value) {
        Node<T> p = sentinel.next;
        while (p != sentinel) {
            if (Objects.equals(p.value, value)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
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
