package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage<SK> extends AbstractStorage<SK> {

    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected final void updateResume(SK key, Resume resume) {
        String mapKey = getMapKey(key, resume);
        storage.put(mapKey, resume);
    }

    @Override
    protected final void insertResume(SK key, Resume resume) {
        String mapKey = getMapKey(key, resume);
        storage.put(mapKey, resume);
    }

    @Override
    protected final Resume getResume(SK key) {
        String mapKey = getMapKey(key, null);
        return storage.get(mapKey);
    }

    @Override
    protected final void deleteResume(SK key) {
        String mapKey = getMapKey(key, null);
        storage.remove(mapKey);
    }

    @Override
    protected List<Resume> getAllResumes() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    protected abstract String getMapKey(SK key, Resume resume);
}