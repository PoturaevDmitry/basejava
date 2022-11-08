package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
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
    void saveWhenStorageOverflow() {
        try {
            while (storage.size() < AbstractArrayStorage.STORAGE_LIMIT) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assertions.fail("переполнение базы произошло раньше времени");
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(RESUME_4));
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
        try {
            storage.delete(UUID_1);
            Assertions.fail();
        } catch (NotExistStorageException e) {
            Assertions.assertNotNull(e);
        }
    }

    @Test
    void deleteWhenResumeNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_NOT_EXIST));
    }

    @Test
    void getAll() {
        Resume[] expected = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
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