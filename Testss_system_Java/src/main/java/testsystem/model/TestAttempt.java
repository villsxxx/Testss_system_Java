package testsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestAttempt {
    private Long id;
    private Long userId;
    private Long testId;
    private Integer score;
    private Timestamp startedAt;
    private Timestamp finishedAt;


}