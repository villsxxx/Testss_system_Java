package testsystem.repository;

import testsystem.model.TestAttempt;
import java.util.List;

public interface TestAttemptRepositoryInterface {
    List<TestAttempt> findAll(int page, int size);
    int count();
    void insert(TestAttempt attempt);
    void update(TestAttempt attempt);
    void delete(long id);
    List<TestAttempt> findByUserId(long userId, int page, int size);
}