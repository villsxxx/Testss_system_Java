package testsystem.repository;

import testsystem.model.UserAnswer;
import java.util.List;

public interface UserAnswerRepositoryInterface {
    List<UserAnswer> findAll(int page, int size);
    int count();
    void insert(UserAnswer userAnswer);
    void update(UserAnswer userAnswer);
    void delete(long id);
    List<UserAnswer> findByAttemptId(long attemptId);
}