package testsystem.model;

public class UserAnswer {
    private Long id;
    private Long attemptId;
    private Long questionId;
    private Long answerId;      // может быть null для текстовых ответов
    private String textAnswer;  // может быть null для выбора

    public UserAnswer() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAttemptId() { return attemptId; }
    public void setAttemptId(Long attemptId) { this.attemptId = attemptId; }

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public Long getAnswerId() { return answerId; }
    public void setAnswerId(Long answerId) { this.answerId = answerId; }

    public String getTextAnswer() { return textAnswer; }
    public void setTextAnswer(String textAnswer) { this.textAnswer = textAnswer; }

    @Override
    public String toString() {
        return "UserAnswer{" +
                "id=" + id +
                ", attemptId=" + attemptId +
                ", questionId=" + questionId +
                ", answerId=" + answerId +
                ", textAnswer='" + textAnswer + '\'' +
                '}';
    }
}