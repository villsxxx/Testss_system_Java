package testsystem.repository;

import testsystem.model.Answer;
import java.util.List;

public interface AnswerRepositoryInterface {
    List<Answer> findAll(int page, int size);
    int count();
    void insert(Answer answer);
    void update(Answer answer);
    void delete(long id);
    List<Answer> findByQuestionId(long questionId);
}