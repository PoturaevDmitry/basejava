package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Резюме с идентификатором " + resume.getUuid() +
                    " в базе отсутствует");
        } else {
            storage[index] = resume;
        }
    }

    @Override
    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != -1) {
            System.out.println("Резюме с идентификатором " + resume.getUuid() + " уже есть в базе");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("База заполнена. Резюме с идентификатором " +
                    resume.getUuid() + " не может быть добавлено");
        } else {
            storage[size++] = resume;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме с идентификатором " + uuid + " в базе отсутствует");
        } else {
            storage[index] = storage[--size];
            storage[size] = null;
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }


    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}