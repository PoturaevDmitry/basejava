package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected final void insertResume(Object key, Resume resume) {
        if (size() == STORAGE_LIMIT) {
            throw new StorageException("База заполнена. Резюме с идентификатором " +
                    resume.getUuid() + " не может быть добавлено", resume.getUuid());
        }
        int index = (Integer) key;
        insertToArrayStorage(index, resume);
        size++;
    }

    @Override
    protected final void deleteResume(Object key) {
        int index = (Integer) key;
        deleteFromArrayStorage(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void updateResume(Object key, Resume resume) {
        int index = (Integer) key;
        storage[index] = resume;
    }

    @Override
    protected Resume getResume(Object key) {
        int index = (Integer) key;
        return storage[index];
    }

    @Override
    protected boolean isExist(Object searchKey) {
        int index = (Integer) searchKey;
        return index >= 0 && index < size;
    }

    @Override
    protected Resume[] getAllResume() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void insertToArrayStorage(int index, Resume resume);

    protected abstract void deleteFromArrayStorage(int index);
}