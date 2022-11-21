package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public abstract class AbstractStorageTest {

    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";
    private static final String FULL_NAME_1 = "A";
    private static final String FULL_NAME_2 = "B";
    private static final String FULL_NAME_3 = "C";
    private static final String FULL_NAME_EMPTY = "";
    protected static final Resume RESUME_1 = new Resume(UUID_1, FULL_NAME_EMPTY);
    protected static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_EMPTY);
    protected static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_EMPTY);
    protected static final Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_EMPTY);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        storage.save(RESUME_1);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        List<Resume> expected = List.of();
        List<Resume> actual = storage.getAllSorted();
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void update() {
        Resume resume = new Resume(UUID_1, FULL_NAME_EMPTY);
        storage.update(resume);
        Assertions.assertSame(resume, storage.get(resume.getUuid()));
    }

    @Test
    void updateWhenResumeNotExist() {
        Resume resume = new Resume(UUID_NOT_EXIST);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(resume));
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    void saveWhenResumeExist() {
        Resume resume = new Resume(UUID_3, FULL_NAME_EMPTY);
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(resume));
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    void getWhenResumeNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_NOT_EXIST));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_1));
    }

    @Test
    void deleteWhenResumeNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_NOT_EXIST));
    }

    @Test
    void getAllSortedByName() {
        RESUME_1.setFullName(FULL_NAME_1);
        RESUME_2.setFullName(FULL_NAME_2);
        RESUME_3.setFullName(FULL_NAME_3);
        List<Resume> expected = List.of(RESUME_1, RESUME_2, RESUME_3);
        List<Resume> actual = storage.getAllSorted();
        assertSize(actual.size());
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void getAllSortedByUuid() {
        RESUME_1.setFullName(FULL_NAME_EMPTY);
        RESUME_2.setFullName(FULL_NAME_EMPTY);
        RESUME_3.setFullName(FULL_NAME_EMPTY);
        List<Resume> expected = List.of(RESUME_1, RESUME_2, RESUME_3);
        List<Resume> actual = storage.getAllSorted();
        assertSize(actual.size());
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void size() {
        assertSize(3);
    }

    private void assertSize(int expected) {
        Assertions.assertEquals(expected, storage.size());
    }

    private void assertGet(Resume expected) {
        Resume actual = storage.get(expected.getUuid());
        Assertions.assertEquals(expected, actual);
    }
}