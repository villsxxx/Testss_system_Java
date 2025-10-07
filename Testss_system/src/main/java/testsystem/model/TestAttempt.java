package testsystem.model;

import java.sql.Timestamp;

public class TestAttempt {
    private Long id;
    private Long userId;
    private Long testId;
    private Integer score;
    private Timestamp startedAt;
    private Timestamp finishedAt;

    public TestAttempt() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getTestId() { return testId; }
    public void setTestId(Long testId) { this.testId = testId; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public Timestamp getStartedAt() { return startedAt; }
    public void setStartedAt(Timestamp startedAt) { this.startedAt = startedAt; }

    public Timestamp getFinishedAt() { return finishedAt; }
    public void setFinishedAt(Timestamp finishedAt) { this.finishedAt = finishedAt; }

    @Override
    public String toString() {
        return "TestAttempt{" +
                "id=" + id +
                ", userId=" + userId +
                ", testId=" + testId +
                ", score=" + score +
                '}';
    }
}