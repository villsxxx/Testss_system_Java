package testsystem.repository;

import testsystem.model.Question;
import java.util.List;

public interface QuestionRepositoryInterface {
    List<Question> findAll(int page, int size);
    int count();
    void insert(Question question);
    void update(Question question);
    void delete(long id);
    List<Question> findByTestId(long testId, int page, int size);
}