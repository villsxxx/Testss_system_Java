package testsystem.repository;

import java.util.List;

public interface BaseRepository<T> {
    List<T> findAll(int page, int size);
    int count();
    void insert(T answer);
    void update(T answer);
    void delete(long id);
    List<T> findById(long id);
}
