package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

class AbstractStorageTest {

    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";
    protected static final Resume RESUME_1 = new Resume(UUID_1);
    protected static final Resume RESUME_2 = new Resume(UUID_2);
    protected static final Resume RESUME_3 = new Resume(UUID_3);
    protected static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
        Resume[] expected = {};
        Resume[] actual = storage.getAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void update() {
        Resume resume = new Resume(UUID_1);
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
        Resume resume = new Resume(UUID_3);
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
    void getAll() {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Resume[] actual = storage.getAll();
        assertSize(actual.length);
        Assertions.assertArrayEquals(expected, actual);
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