package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    protected void shiftRight(int fromIndex) {
        System.arraycopy(storage, fromIndex, storage, fromIndex + 1, size - fromIndex);
    }

    @Override
    protected void eraseResume(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}