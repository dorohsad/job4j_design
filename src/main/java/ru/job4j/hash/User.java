package ru.job4j.hash;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User userOne = new User("Alex", 2,
                new GregorianCalendar(2014, Calendar.FEBRUARY, 21));
        User userTwo = new User("Alex", 2,
                new GregorianCalendar(2014, Calendar.FEBRUARY, 21));
        Map<User, Object> map = new HashMap<>();
        map.put(userOne, new Object());
        map.put(userTwo, new Object());
        System.out.println(map);
        System.out.println(indexFor(Objects.hash(userOne), 16));
        System.out.println(indexFor(Objects.hash(userTwo), 16));
    }

    static int indexFor(int h, int length) {
        return h & (length - 1);
    }
}
