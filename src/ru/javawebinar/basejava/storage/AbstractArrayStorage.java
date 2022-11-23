package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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
    protected final void insertResume(Integer key, Resume resume) {
        if (size() == STORAGE_LIMIT) {
            throw new StorageException("База заполнена. Резюме с идентификатором " +
                    resume.getUuid() + " не может быть добавлено", resume.getUuid());
        }
        insertToArrayStorage(key, resume);
        size++;
    }

    @Override
    protected final void deleteResume(Integer key) {
        deleteFromArrayStorage(key);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void updateResume(Integer key, Resume resume) {
        storage[key] = resume;
    }

    @Override
    protected Resume getResume(Integer key) {
        return storage[key];
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0 && searchKey < size;
    }

    @Override
    protected List<Resume> getAllResumes() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    protected abstract void insertToArrayStorage(int index, Resume resume);

    protected abstract void deleteFromArrayStorage(int index);
}