import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        storage[size++] = resume;
    }

    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index == -1) return null;
        return storage[index];
    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index == -1) return;
        size--;
        if (index < storage.length - 1) System.arraycopy(storage, index + 1, storage, index, size - index);
        storage[size] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) return i;
        }
        return -1;
    }
}