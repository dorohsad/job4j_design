package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            getContainer();
        }
        container[size++] = value;
        modCount++;
    }


    @Override
    public T set(int index, T newValue) {
        get(index);
        container[index] = newValue;
        return container[index];
    }

    @Override
    public T remove(int index) {
        T temp = get(index);
        System.arraycopy(container, index + 1,
                container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return temp;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int point;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }

    private void getContainer() {
        container = Arrays.copyOf(container, container.length * 2 + 1);
    }
}

