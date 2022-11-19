package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
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
        return index >= 0;
    }

    @Override
    protected Stream<Resume> getAllResumes() {
        return storage.stream();
    }
}