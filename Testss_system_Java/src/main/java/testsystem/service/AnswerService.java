package testsystem.service;

import lombok.RequiredArgsConstructor;
import testsystem.model.Answer;

import testsystem.repository.BaseRepository;

import java.util.List;
@RequiredArgsConstructor
public class AnswerService {

    private final BaseRepository<Answer> answerRepository;

    public List<Answer> getAllAnswers(int page, int size) {
        return answerRepository.findAll(page, size);
    }

    public int getTotalAnswersCount() {
        return answerRepository.count();
    }

    public void createAnswer(Answer answer) {
        if (answer.getQuestionId() == null || answer.getQuestionId() <= 0) {
            throw new IllegalArgumentException("ID вопроса не может быть пустым или отрицательным");
        }
        if (answer.getText() == null || answer.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Текст ответа не может быть пустым");
        }
        if (answer.getPoints() < 0) {
            throw new IllegalArgumentException("Баллы не могут быть отрицательными");
        }
        answerRepository.insert(answer);
    }

    public void updateAnswer(Answer answer) {
        if (answer.getId() == null || answer.getId() <= 0) {
            throw new IllegalArgumentException("ID ответа не может быть пустым или отрицательным");
        }
        if (answer.getQuestionId() == null || answer.getQuestionId() <= 0) {
            throw new IllegalArgumentException("ID вопроса не может быть пустым или отрицательным");
        }
        if (answer.getText() == null || answer.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Текст ответа не может быть пустым");
        }
        if (answer.getPoints() < 0) {
            throw new IllegalArgumentException("Баллы не могут быть отрицательными");
        }
        answerRepository.update(answer);
    }

    public void deleteAnswer(long id) {
        answerRepository.delete(id);
    }

    public List<Answer> getAnswersByQuestionId(long questionId) {
        return answerRepository.findById(questionId);
    }
}