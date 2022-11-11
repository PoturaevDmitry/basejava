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
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected final void insertResume(int index, Resume resume) {
        if (size() == STORAGE_LIMIT) {
            throw new StorageException("База заполнена. Резюме с идентификатором " +
                    resume.getUuid() + " не может быть добавлено", resume.getUuid());
        }
        insertToArrayStorage(index, resume);
        size++;
    }

    @Override
    protected final void deleteResume(int index) {
        deleteFromArrayStorage(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void updateResume(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected Resume getResume(int index) {
        return storage[index];
    }

    protected abstract void insertToArrayStorage(int index, Resume resume);

    protected abstract void deleteFromArrayStorage(int index);
}