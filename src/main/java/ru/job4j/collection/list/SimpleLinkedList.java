package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private SimpleLinkedList.Node<E> first;
    private SimpleLinkedList.Node<E> last;
    int size;
    int modCount;

    @Override
    public void add(E value) {
        final SimpleLinkedList.Node<E> l = last;
        final SimpleLinkedList.Node<E> newNode = new SimpleLinkedList.Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return node(index).item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private SimpleLinkedList.Node<E> next = first;
            private int nextIndex;
            private final int expectedModCount = modCount;

            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return nextIndex < size;
            }

            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> lastReturned = next;
                next = next.next;
                nextIndex++;
                return lastReturned.item;
            }
        };
    }


    SimpleLinkedList.Node<E> node(int index) {

        SimpleLinkedList.Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private static class Node<E> {
        E item;
        SimpleLinkedList.Node<E> next;
        SimpleLinkedList.Node<E> prev;

        Node(SimpleLinkedList.Node<E> prev, E element, SimpleLinkedList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}