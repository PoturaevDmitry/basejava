package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import static java.util.Objects.nonNull;

public class MapResumeStorage extends AbstractMapStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected String getMapKey(Object key, Resume resume) {
        return nonNull(key) ? ((Resume) key).getUuid() : resume.getUuid();
    }
}