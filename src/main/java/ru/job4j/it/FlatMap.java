package ru.job4j.it;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    int index;
    List<T> list;

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        list = flat(data);
    }

    @SuppressWarnings("checkstyle:EmptyForIteratorPad")
    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return list.get(index++);
    }

    public List<T> flat(Iterator<Iterator<T>> data) {
        return asStream(data)
                .flatMap(FlatMap::asStream)
                .collect(Collectors.toList());
    }

    public static <T> Stream<T> asStream(Iterator<T> source) {
        Iterable<T> iterable = () -> source;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }
}
