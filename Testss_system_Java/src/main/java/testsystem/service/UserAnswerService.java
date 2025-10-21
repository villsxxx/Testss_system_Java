package testsystem.service;

import lombok.RequiredArgsConstructor;
import testsystem.model.UserAnswer;
import testsystem.repository.BaseRepository;

import java.util.List;
@RequiredArgsConstructor
public class UserAnswerService {
    private final BaseRepository<UserAnswer> userAnswerRepository;





    public List<UserAnswer> getAllUserAnswers(int page, int size) {
        return userAnswerRepository.findAll(page, size);
    }

    public int getTotalUserAnswersCount() {
        return userAnswerRepository.count();
    }

    public void createUserAnswer(UserAnswer userAnswer) {
        if (userAnswer.getAttemptId() == null || userAnswer.getAttemptId() <= 0) {
            throw new IllegalArgumentException("ID попытки не может быть пустым или отрицательным");
        }
        if (userAnswer.getQuestionId() == null || userAnswer.getQuestionId() <= 0) {
            throw new IllegalArgumentException("ID вопроса не может быть пустым или отрицательным");
        }
        if (userAnswer.getAnswerId() == null && (userAnswer.getTextAnswer() == null || userAnswer.getTextAnswer().trim().isEmpty())) {
            throw new IllegalArgumentException("Должен быть указан либо answer_id, либо text_answer");
        }
        userAnswerRepository.insert(userAnswer);
    }

    public void updateUserAnswer(UserAnswer userAnswer) {
        if (userAnswer.getId() == null || userAnswer.getId() <= 0) {
            throw new IllegalArgumentException("ID ответа пользователя не может быть пустым или отрицательным");
        }
        if (userAnswer.getAttemptId() == null || userAnswer.getAttemptId() <= 0) {
            throw new IllegalArgumentException("ID попытки не может быть пустым или отрицательным");
        }
        if (userAnswer.getQuestionId() == null || userAnswer.getQuestionId() <= 0) {
            throw new IllegalArgumentException("ID вопроса не может быть пустым или отрицательным");
        }
        if (userAnswer.getAnswerId() == null && (userAnswer.getTextAnswer() == null || userAnswer.getTextAnswer().trim().isEmpty())) {
            throw new IllegalArgumentException("Должен быть указан либо answer_id, либо text_answer");
        }
        userAnswerRepository.update(userAnswer);
    }

    public void deleteUserAnswer(long id) {
        userAnswerRepository.delete(id);
    }

    public List<UserAnswer> getUserAnswersByAttemptId(long attemptId) {
        return userAnswerRepository.findById(attemptId);
    }
}