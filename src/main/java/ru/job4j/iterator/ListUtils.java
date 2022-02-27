package ru.job4j.iterator;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

public class ListUtils {
    public static <T> void addBefore(List<T> list, int index, T value) {
        ListIterator<T> iterator = getIterator(list, index);
        iterator.add(value);
    }


    public static <T> void addAfter(List<T> list, int index, T value) {
        ListIterator<T> iterator = getIterator(list, index);
        iterator.next();
        iterator.add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (filter.test(next)) {
                iterator.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (filter.test(next)) {
                iterator.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (elements.contains(next)) {
                iterator.remove();
            }
        }
    }

    private static <T> ListIterator<T> getIterator(List<T> list, int index) {
        Objects.checkIndex(index, list.size());
        return list.listIterator(index);
    }
}