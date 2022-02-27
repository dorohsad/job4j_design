package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfterFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 0, 3);
        assertThat(input, is(Arrays.asList(0, 3, 1, 2)));
    }

    @Test
    public void whenDeleteMoreTwo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 3, 2, 4));
        ListUtils.removeIf(input, x -> x > 2);
        assertThat(input, is(Arrays.asList(0, 2)));
    }

    @Test
    public void whenDeleteAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 3, 2, 4));
        List<Integer> inputDelete = new ArrayList<>(Arrays.asList(0, 2));
        ListUtils.removeAll(input, inputDelete);
        assertThat(input, is(Arrays.asList(3, 4)));
    }

    @Test
    public void whenReplaceMoreTwo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 3, 2, 4));
        ListUtils.replaceIf(input, x -> x > 2, 10);
        assertThat(input, is(Arrays.asList(0, 10, 2, 10)));
    }
}