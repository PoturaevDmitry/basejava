package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection {
    private final List<String> list = new ArrayList<>();

    public ListSection() {
    }

    public List<String> getList() {
        return list;
    }

    public void addString(String string) {
        list.add(string);
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "list=" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }
}