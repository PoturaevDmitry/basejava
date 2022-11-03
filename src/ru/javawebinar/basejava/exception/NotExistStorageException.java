package ru.javawebinar.basejava.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Резюме с идентификатором " + uuid + " в базе отсутствует", uuid);
    }
}