package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid, "dummy"),
                Comparator.comparing(Resume::getUuid));
    }

    @Override
    protected void insertToArrayStorage(int index, Resume resume) {
        index = Math.abs(index) - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = resume;
    }

    @Override
    protected void deleteFromArrayStorage(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}