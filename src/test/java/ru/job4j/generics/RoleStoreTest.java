package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsTzar() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Tzar"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Tzar"));
    }

    @Test
    public void whenAddAndNotFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Tzar"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsTzar() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Tzar"));
        store.add(new Role("1", "Snowman"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Tzar"));
    }

    @Test
    public void whenReplaceThenRoleIsSnowman() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Tzar"));
        store.replace("1", new Role("1", "Snowman"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Snowman"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Tzar"));
        store.replace("10", new Role("10", "Snowman"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Tzar"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Tzar"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsTzar() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Tzar"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Tzar"));
    }
}