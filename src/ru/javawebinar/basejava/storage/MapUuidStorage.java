package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

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
        storage.put((String) key, resume);
    }

    @Override
    protected final void insertResume(Object key, Resume resume) {
        storage.put((String) key, resume);
    }

    @Override
    protected final void deleteResume(Object key) {
        storage.remove((String) key);
    }

    @Override
    protected final Resume getResume(Object key) {
        return storage.get((String) key);
    }

    @Override
    protected List<Resume> getAllResumes() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((String) searchKey);
    }
}