package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SortedArrayStorage;
import ru.javawebinar.basejava.storage.Storage;

/**
 * Test ru.javawebinar.basejava.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    static final Storage STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume resume1 = new Resume("uuid3");
        Resume resume2 = new Resume("uuid1");
        Resume resume3 = new Resume("uuid2");

        STORAGE.save(resume1);
        STORAGE.save(resume2);
        STORAGE.save(resume3);

        System.out.println("Get r1: " + STORAGE.get(resume1.getUuid()));
        System.out.println("Size: " + STORAGE.size());

        System.out.println("Get dummy: " + STORAGE.get("dummy"));

        printAll();
        STORAGE.update(new Resume("dummy"));
        STORAGE.delete(resume1.getUuid());
        printAll();
        STORAGE.clear();
        printAll();

        System.out.println("Size: " + STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume resume : STORAGE.getAllSorted()) {
            System.out.println(resume);
        }
    }
}