package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import static java.util.Objects.nonNull;

public class MapResumeStorage extends AbstractMapStorage<Resume> {

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    protected String getMapKey(Resume key, Resume resume) {
        return nonNull(key) ? key.getUuid() : resume.getUuid();
    }
}