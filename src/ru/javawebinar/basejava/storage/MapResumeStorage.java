package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractMapStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        return new Resume(uuid);
    }
}
