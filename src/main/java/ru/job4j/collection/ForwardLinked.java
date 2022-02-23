package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public boolean isEmpty() {
        return head == null;
    }

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public T deleteFirst() {
        checkEmpty();
        Node<T> node = head;
        head = node.next;
        T value = node.value;
        node.next = null;
        node.value = null;
        return value;
    }

    public T deleteLast() {
        checkEmpty();
        Node<T> tail = head;
        Node<T> prev = head;
        while (tail.next != null) {
            prev = tail;
            tail = tail.next;
        }
        T value = tail.value;
        tail.value = null;
        prev.next = null;
        return value;
    }

    private void checkEmpty() {
        if (head == null) {
            throw new NoSuchElementException();
        }
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
