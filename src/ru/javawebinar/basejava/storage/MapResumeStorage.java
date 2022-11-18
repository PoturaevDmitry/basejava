package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private final Map<Resume, Resume> storage = new HashMap<>();

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
        return new Resume(uuid);
    }

    @Override
    protected void updateResume(Object key, Resume resume) {
        storage.put((Resume) key, resume);
    }

    @Override
    protected void insertResume(Object key, Resume resume) {
        storage.put((Resume) key, resume);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove((Resume) key);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get((Resume) key);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey((Resume) searchKey);
    }

    @Override
    protected Resume[] getAllResume() {
        Collection<Resume> resumes = storage.values();
        return resumes.toArray(new Resume[0]);
    }
}
