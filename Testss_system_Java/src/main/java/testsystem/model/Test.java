package testsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Test {
    private Long id;
    private String title;
    private String description;
    private Boolean active;
    private Timestamp createdAt;

}