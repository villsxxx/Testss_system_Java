package testsystem.service;

import lombok.RequiredArgsConstructor;
import testsystem.model.Question;
import testsystem.repository.BaseRepository;

import java.util.List;
@RequiredArgsConstructor
public class QuestionService {

    private final BaseRepository<Question> questionRepository;

    public List<Question> getAllQuestions(int page, int size) {
        return questionRepository.findAll(page, size);
    }

    public int getTotalQuestionsCount() {
        return questionRepository.count();
    }

    public void createQuestion(Question question) {
        if (question.getTestId() == null || question.getTestId() <= 0) {
            throw new IllegalArgumentException("ID теста не может быть пустым или отрицательным");
        }
        if (question.getText() == null || question.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Текст вопроса не может быть пустым");
        }
        String[] validTypes = {"single_choice", "multiple_choice", "text", "true_false"};
        boolean isValidType = false;
        for (String type : validTypes) {
            if (type.equals(question.getType())) {
                isValidType = true;
                break;
            }
        }
        if (!isValidType) {
            throw new IllegalArgumentException("Недопустимый тип вопроса. Допустимые: " + String.join(", ", validTypes));
        }
        questionRepository.insert(question);
    }

    public void updateQuestion(Question question) {
        if (question.getId() == null || question.getId() <= 0) {
            throw new IllegalArgumentException("ID вопроса не может быть пустым или отрицательным");
        }
        if (question.getTestId() == null || question.getTestId() <= 0) {
            throw new IllegalArgumentException("ID теста не может быть пустым или отрицательным");
        }
        if (question.getText() == null || question.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Текст вопроса не может быть пустым");
        }
        String[] validTypes = {"single_choice", "multiple_choice", "text", "true_false"};
        boolean isValidType = false;
        for (String type : validTypes) {
            if (type.equals(question.getType())) {
                isValidType = true;
                break;
            }
        }
        if (!isValidType) {
            throw new IllegalArgumentException("Недопустимый тип вопроса. Допустимые: " + String.join(", ", validTypes));
        }
        questionRepository.update(question);
    }

    public void deleteQuestion(long id) {
        questionRepository.delete(id);
    }

    public List<Question> getQuestionsByTestId(long testId) {
        return questionRepository.findById(testId);
    }
}