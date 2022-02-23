package ru.job4j.collection;

public class SimpleQueue<T> {
    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        while (!in.isEmpty()) {
            T pop = in.pop();
            out.push(pop);
        }
        T rsl = out.pop();
        in = out;
        out = new SimpleStack<>();
        return rsl;
    }

    public void push(T value) {
        in.push(value);
    }
}
