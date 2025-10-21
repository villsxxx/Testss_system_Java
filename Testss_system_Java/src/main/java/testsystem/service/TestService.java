package testsystem.service;

import lombok.RequiredArgsConstructor;
import testsystem.model.Test;
import testsystem.repository.BaseRepository;

import java.util.List;
@RequiredArgsConstructor
public class TestService {
    private final BaseRepository<Test> testRepository; // ← теперь интерфейс!




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

    public List<Test> searchTests(long id) {

        return testRepository.findById(id);
    }
}