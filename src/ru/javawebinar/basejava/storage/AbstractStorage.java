package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    protected static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public final void update(Resume resume) {
        LOG.info("Update " + resume);
        SK key = getExistingSearchKey(resume.getUuid());
        updateResume(key, resume);
    }

    @Override
    public final void save(Resume resume) {
        LOG.info("Save " + resume);
        SK key = getNotExistingSearchKey(resume.getUuid());
        insertResume(key, resume);
    }

    @Override
    public final Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK key = getExistingSearchKey(uuid);
        return getResume(key);
    }

    @Override
    public final void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK key = getExistingSearchKey(uuid);
        deleteResume(key);
    }

    @Override
    public final List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> resumes = getAllResumes();
        resumes.sort(Comparator.naturalOrder());
        return resumes;
    }

    private SK getExistingSearchKey(String uuid) {
        SK key = getSearchKey(uuid);
        if (!isExist(key)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private SK getNotExistingSearchKey(String uuid) {
        SK key = getSearchKey(uuid);
        if (isExist(key)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract void updateResume(SK key, Resume resume);

    protected abstract void insertResume(SK key, Resume resume);

    protected abstract void deleteResume(SK key);

    protected abstract Resume getResume(SK key);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Resume> getAllResumes();
}