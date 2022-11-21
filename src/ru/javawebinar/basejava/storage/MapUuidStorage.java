package ru.javawebinar.basejava.storage;

public class MapUuidStorage extends AbstractMapStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected String getMapKey(Object key) {
        return (String) key;
    }
}