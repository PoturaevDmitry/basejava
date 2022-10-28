package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Резюме с идентификатором " + resume.getUuid() + " уже есть в базе");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("База заполнена. Резюме с идентификатором " +
                    resume.getUuid() + " не может быть добавлено");
        } else {
            index = Math.abs(index) - 1;
            if (index != size) {
                System.arraycopy(storage, index, storage, index + 1, size - index);
            }
            storage[index] = resume;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Резюме с идентификатором " + uuid + " в базе отсутствует");
        } else {
            if (size != 1 && index + 1 < size) {
                System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            }
            storage[--size] = null;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }
}