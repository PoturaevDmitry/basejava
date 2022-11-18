package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMapStorage extends AbstractStorage {

    protected final Map<Object, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void updateResume(Object key, Resume resume) {
        storage.put(key, resume);
    }

    @Override
    protected void insertResume(Object key, Resume resume) {
        storage.put(key, resume);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove(key);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get(key);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected Resume[] getAllResume() {
        Collection<Resume> resumes = storage.values();
        return resumes.toArray(new Resume[0]);
    }
}