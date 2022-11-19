package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractMapStorage {

    @Override
    protected void updateResume(Object key, Resume resume) {
        storage.put(((Resume) key).getUuid(), resume);
    }

    @Override
    protected void insertResume(Object key, Resume resume) {
        storage.put(((Resume) key).getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object key) {
        storage.remove(((Resume) key).getUuid());
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get(((Resume) key).getUuid());
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return new Resume(uuid, "");
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(((Resume) searchKey).getUuid());
    }
}