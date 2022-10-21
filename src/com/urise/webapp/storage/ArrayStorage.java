package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (indexOf(resume.getUuid()) != -1) {
            System.out.println("Резюме с идентификатором " + resume.getUuid() + " уже есть в базе");
        } else if (size == storage.length) {
            System.out.println("База заполнена. Резюме с идентификатором " +
                    resume.getUuid() + " не может быть добавлено");
        } else {
            storage[size++] = resume;
        }
    }

    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index != -1) return new Resume(storage[index].getUuid());
        System.out.println("Резюме с идентификатором " + uuid + " в базе отсутствует");
        return null;
    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index != -1) {
            if (index < storage.length - 1) {
                System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            }
            storage[--size] = null;
        } else {
            System.out.println("Резюме с идентификатором " + uuid + " в базе отсутствует");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = indexOf(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Резюме с идентификатором " + resume.getUuid() + " в базе отсутствует");
        }
    }

    private int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) return i;
        }
        return -1;
    }
}