package testsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAnswer {
    private Long id;
    private Long attemptId;
    private Long questionId;
    private Long answerId;      // может быть null для текстовых ответов
    private String textAnswer;  // может быть null для выбора

}