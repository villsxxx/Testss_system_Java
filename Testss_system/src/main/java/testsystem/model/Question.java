package testsystem.model;

public class Question {
    private Long id;
    private Long testId;
    private String text;
    private String type; // single_choice, multiple_choice, text, true_false

    public Question() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTestId() { return testId; }
    public void setTestId(Long testId) { this.testId = testId; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", testId=" + testId +
                ", type='" + type + '\'' +
                '}';
    }
}