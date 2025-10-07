package testsystem.service;

import testsystem.model.Test;
import testsystem.repository.TestRepository;
import testsystem.repository.TestRepositoryInterface;
import java.util.List;

public class TestService {
    private final TestRepositoryInterface testRepository; // ← теперь интерфейс!

    public TestService() {
        this.testRepository = new TestRepository(); // ← пока создаём реализацию
    }

    // Можно передавать репозиторий через конструктор для гибкости
    public TestService(TestRepositoryInterface testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getAllTests(int page, int size) {
        return testRepository.findAll(page, size);
    }

    public int getTotalTestsCount() {
        return testRepository.count();
    }

    public void createTest(Test test) {
        if (test.getTitle() == null || test.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Название теста не может быть пустым");
        }
        if (test.getDescription() == null) {
            test.setDescription("");
        }
        if (test.getActive() == null) {
            test.setActive(true);
        }
        testRepository.insert(test);
    }

    public void updateTest(Test test) {
        if (test.getId() == null || test.getId() <= 0) {
            throw new IllegalArgumentException("ID теста не может быть пустым или отрицательным");
        }
        if (test.getTitle() == null || test.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Название теста не может быть пустым");
        }
        testRepository.update(test);
    }

    public void deleteTest(long id) {
        testRepository.delete(id);
    }

    public List<Test> searchTests(String keyword, int page, int size) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllTests(page, size);
        }
        return testRepository.search(keyword, page, size);
    }
}