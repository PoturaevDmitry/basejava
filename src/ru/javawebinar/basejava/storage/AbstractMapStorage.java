package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage extends AbstractStorage {

    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected final void updateResume(Object key, Resume resume) {
        String mapKey = getMapKey(key);
        storage.put(mapKey, resume);
    }

    @Override
    protected final void insertResume(Object key, Resume resume) {
        String mapKey = getMapKey(key);
        storage.put(mapKey, resume);
    }

    @Override
    protected final void deleteResume(Object key) {
        String mapKey = getMapKey(key);
        storage.remove(mapKey);
    }

    @Override
    protected final Resume getResume(Object key) {
        String mapKey = getMapKey(key);
        return storage.get(mapKey);
    }

    @Override
    protected final boolean isExist(Object searchKey) {
        String mapKey = getMapKey(searchKey);
        return storage.containsKey(mapKey);
    }

    @Override
    protected List<Resume> getAllResumes() {
        return new ArrayList<>(storage.values());
    }

    protected abstract String getMapKey(Object key);
}