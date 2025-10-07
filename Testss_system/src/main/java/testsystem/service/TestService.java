package testsystem.service;

import testsystem.model.Test;
import testsystem.repository.TestRepository;
import java.util.List;

public class TestService {
    private final TestRepository testRepository = new TestRepository();

    public List<Test> getAllTests(int page, int size) {
        return testRepository.findAll(page, size);
    }

    public int getTotalTestsCount() {
        return testRepository.count();
    }

    public void createTest(Test test) {
        // Валидация: название не может быть пустым
        if (test.getTitle() == null || test.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Название теста не может быть пустым");
        }
        // Описание по умолчанию — пустая строка
        if (test.getDescription() == null) {
            test.setDescription("");
        }
        // Активен по умолчанию — true
        if (test.getActive() == null) {
            test.setActive(true);
        }
        testRepository.insert(test);
    }

    public void updateTest(Test test) {
        // Валидация ID
        if (test.getId() == null || test.getId() <= 0) {
            throw new IllegalArgumentException("ID теста не может быть пустым или отрицательным");
        }
        // Валидация названия
        if (test.getTitle() == null || test.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Название теста не может быть пустым");
        }
        testRepository.update(test);
    }

    public void deleteTest(long id) {
        // Можно добавить проверку: есть ли попытки? Но по ТЗ — пока просто удаляем
        testRepository.delete(id);
    }

    public List<Test> searchTests(String keyword, int page, int size) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllTests(page, size); // если пустой запрос — возвращаем всё
        }
        return testRepository.search(keyword, page, size);
    }
}