package testsystem.service;

import lombok.RequiredArgsConstructor;
import testsystem.model.TestAttempt;
import testsystem.repository.BaseRepository;

import java.util.List;
@RequiredArgsConstructor
public class TestAttemptService {
    private final BaseRepository<TestAttempt> testAttemptRepository;

    public List<TestAttempt> getAllAttempts(int page, int size) {
        return testAttemptRepository.findAll(page, size);
    }

    public int getTotalAttemptsCount() {
        return testAttemptRepository.count();
    }

    public void createAttempt(TestAttempt attempt) {
        if (attempt.getUserId() == null || attempt.getUserId() <= 0) {
            throw new IllegalArgumentException("ID пользователя не может быть пустым или отрицательным");
        }
        if (attempt.getTestId() == null || attempt.getTestId() <= 0) {
            throw new IllegalArgumentException("ID теста не может быть пустым или отрицательным");
        }
        if (attempt.getScore() < 0) {
            throw new IllegalArgumentException("Баллы не могут быть отрицательными");
        }
        if (attempt.getStartedAt() == null) {
            throw new IllegalArgumentException("Дата начала не может быть пустой");
        }
        if (attempt.getFinishedAt() == null) {
            throw new IllegalArgumentException("Дата окончания не может быть пустой");
        }
        testAttemptRepository.insert(attempt);
    }

    public void updateAttempt(TestAttempt attempt) {
        if (attempt.getId() == null || attempt.getId() <= 0) {
            throw new IllegalArgumentException("ID попытки не может быть пустым или отрицательным");
        }
        if (attempt.getUserId() == null || attempt.getUserId() <= 0) {
            throw new IllegalArgumentException("ID пользователя не может быть пустым или отрицательным");
        }
        if (attempt.getTestId() == null || attempt.getTestId() <= 0) {
            throw new IllegalArgumentException("ID теста не может быть пустым или отрицательным");
        }
        if (attempt.getScore() < 0) {
            throw new IllegalArgumentException("Баллы не могут быть отрицательными");
        }
        if (attempt.getStartedAt() == null) {
            throw new IllegalArgumentException("Дата начала не может быть пустой");
        }
        if (attempt.getFinishedAt() == null) {
            throw new IllegalArgumentException("Дата окончания не может быть пустой");
        }
        testAttemptRepository.update(attempt);
    }

    public void deleteAttempt(long id) {
        testAttemptRepository.delete(id);
    }

    public List<TestAttempt> getAttemptsByUserId(long userId) {
        return testAttemptRepository.findById(userId);
    }
}