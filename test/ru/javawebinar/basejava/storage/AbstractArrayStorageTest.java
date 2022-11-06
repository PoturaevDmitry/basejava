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

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
    }

    @Test
    public void updateWhenResumeNotExist() {
        Resume resume = new Resume("dummy");
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.update(resume));
    }

    @Test
    public void save() {
        Resume resume = new Resume("uuid4");
        storage.save(resume);
        Assertions.assertEquals(4, storage.size());
    }

    @Test
    public void saveWhenResumeExist() {
        Resume resume = new Resume("uuid3");
        Assertions.assertThrows(ExistStorageException.class, () -> storage.save(resume));
    }

    @Test
    public void saveWhenStorageOverflow() {
        try {
            while (storage.size() < AbstractArrayStorage.STORAGE_LIMIT) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assertions.fail("переполнение базы произошло раньше времени");
        }
        Resume resume = new Resume("uuid");
        Assertions.assertThrows(StorageException.class, () -> storage.save(resume));
    }

    @Test
    public void get() {
        Resume expected = new Resume(UUID_1);
        Resume actual = storage.get(UUID_1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getWhenResumeNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        Assertions.assertEquals(2, storage.size());
    }

    @Test
    public void deleteWhenResumeNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assertions.assertEquals(storage.size(), resumes.length);
        Assertions.assertEquals(storage.get(resumes[0].getUuid()), resumes[0]);
        Assertions.assertEquals(storage.get(resumes[1].getUuid()), resumes[1]);
        Assertions.assertEquals(storage.get(resumes[2].getUuid()), resumes[2]);
    }

    @Test
    public void size() {
        Assertions.assertEquals(3, storage.size());
    }
}