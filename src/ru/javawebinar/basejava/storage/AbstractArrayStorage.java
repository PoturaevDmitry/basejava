package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Резюме с идентификатором " + resume.getUuid() +
                    " в базе отсутствует");
        } else {
            storage[index] = resume;
        }
    }

    @Override
    public final void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Резюме с идентификатором " + resume.getUuid() + " уже есть в базе");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("База заполнена. Резюме с идентификатором " +
                    resume.getUuid() + " не может быть добавлено");
        } else {
            insertResume(index, resume);
            size++;
        }
    }

    @Override
    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме с идентификатором " + uuid + " в базе отсутствует");
            return null;
        }
        return storage[index];
    }

    @Override
    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме с идентификатором " + uuid + " в базе отсутствует");
        } else {
            deleteResume(index);
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(int index, Resume resume);

    protected abstract void deleteResume(int index);
}