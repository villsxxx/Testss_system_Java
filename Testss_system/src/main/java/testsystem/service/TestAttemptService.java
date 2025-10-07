package testsystem.service;

import testsystem.model.TestAttempt;
import testsystem.repository.TestAttemptRepository;
import java.util.List;

public class TestAttemptService {
    private final TestAttemptRepository testAttemptRepository = new TestAttemptRepository();

    public List<TestAttempt> getAllAttempts(int page, int size) {
        return testAttemptRepository.findAll(page, size);
    }

    public int getTotalAttemptsCount() {
        return testAttemptRepository.count();
    }

    public void createAttempt(TestAttempt attempt) {
        // Валидация userId
        if (attempt.getUserId() == null || attempt.getUserId() <= 0) {
            throw new IllegalArgumentException("ID пользователя не может быть пустым или отрицательным");
        }
        // Валидация testId
        if (attempt.getTestId() == null || attempt.getTestId() <= 0) {
            throw new IllegalArgumentException("ID теста не может быть пустым или отрицательным");
        }
        // Валидация баллов
        if (attempt.getScore() < 0) {
            throw new IllegalArgumentException("Баллы не могут быть отрицательными");
        }
        // Валидация даты начала
        if (attempt.getStartedAt() == null) {
            throw new IllegalArgumentException("Дата начала не может быть пустой");
        }
        // Валидация даты окончания
        if (attempt.getFinishedAt() == null) {
            throw new IllegalArgumentException("Дата окончания не может быть пустой");
        }
        testAttemptRepository.insert(attempt);
    }

    public void updateAttempt(TestAttempt attempt) {
        // Валидация ID
        if (attempt.getId() == null || attempt.getId() <= 0) {
            throw new IllegalArgumentException("ID попытки не может быть пустым или отрицательным");
        }
        // Валидация userId
        if (attempt.getUserId() == null || attempt.getUserId() <= 0) {
            throw new IllegalArgumentException("ID пользователя не может быть пустым или отрицательным");
        }
        // Валидация testId
        if (attempt.getTestId() == null || attempt.getTestId() <= 0) {
            throw new IllegalArgumentException("ID теста не может быть пустым или отрицательным");
        }
        // Валидация баллов
        if (attempt.getScore() < 0) {
            throw new IllegalArgumentException("Баллы не могут быть отрицательными");
        }
        // Валидация даты начала
        if (attempt.getStartedAt() == null) {
            throw new IllegalArgumentException("Дата начала не может быть пустой");
        }
        // Валидация даты окончания
        if (attempt.getFinishedAt() == null) {
            throw new IllegalArgumentException("Дата окончания не может быть пустой");
        }
        testAttemptRepository.update(attempt);
    }

    public void deleteAttempt(long id) {
        testAttemptRepository.delete(id);
    }

    public List<TestAttempt> getAttemptsByUserId(long userId, int page, int size) {
        return testAttemptRepository.findByUserId(userId, page, size);
    }
}