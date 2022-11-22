package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void update(Resume resume) {
        Object key = getExistingSearchKey(resume.getUuid());
        updateResume(key, resume);
    }

    @Override
    public final void save(Resume resume) {
        Object key = getNotExistingSearchKey(resume.getUuid());
        insertResume(key, resume);
    }

    @Override
    public final Resume get(String uuid) {
        Object key = getExistingSearchKey(uuid);
        return getResume(key);
    }

    @Override
    public final void delete(String uuid) {
        Object key = getExistingSearchKey(uuid);
        deleteResume(key);
    }

    @Override
    public final List<Resume> getAllSorted() {
        List<Resume> resumes = getAllResumes();
        resumes.sort(Comparator.naturalOrder());
        return resumes;
    }

    private Object getExistingSearchKey(String uuid) {
        Object key = getSearchKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object key = getSearchKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void updateResume(Object key, Resume resume);

    protected abstract void insertResume(Object key, Resume resume);

    protected abstract void deleteResume(Object key);

    protected abstract Resume getResume(Object key);

    protected abstract boolean isExist(Object searchKey);

    protected abstract List<Resume> getAllResumes();
}