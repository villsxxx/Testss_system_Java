package testsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {
    private Long id;
    private Long testId;
    private String text;
    private String type; // single_choice, multiple_choice, text, true_false

}