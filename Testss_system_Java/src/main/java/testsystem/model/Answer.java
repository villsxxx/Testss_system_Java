package testsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Answer {
    private Long id;
    private Long questionId;
    private String text;
    private Boolean correct;
    private Integer points;


}