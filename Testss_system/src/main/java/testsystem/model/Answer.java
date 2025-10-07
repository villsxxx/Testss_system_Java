package testsystem.model;

public class Answer {
    private Long id;
    private Long questionId;
    private String text;
    private Boolean correct;
    private Integer points;

    public Answer() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Boolean getCorrect() { return correct; }
    public void setCorrect(Boolean correct) { this.correct = correct; }

    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", correct=" + correct +
                ", points=" + points +
                '}';
    }
}