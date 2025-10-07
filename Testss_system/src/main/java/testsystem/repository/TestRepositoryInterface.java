package testsystem.repository;

import testsystem.model.Test;
import java.util.List;

public interface TestRepositoryInterface {
    List<Test> findAll(int page, int size);
    int count();
    void insert(Test test);
    void update(Test test);
    void delete(long id);
    List<Test> search(String keyword, int page, int size);
}