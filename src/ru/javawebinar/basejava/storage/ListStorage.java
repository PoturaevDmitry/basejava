package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected void updateResume(Object key, Resume resume) {
        int index = (Integer) key;
        storage.set(index, resume);
    }

    @Override
    protected void insertResume(Object key, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void deleteResume(Object key) {
        int index = (Integer) key;
        storage.remove(index);
    }

    @Override
    protected Resume getResume(Object key) {
        int index = (Integer) key;
        return storage.get(index);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        int index = (Integer) searchKey;
        return index >= 0 && index < storage.size();
    }
}