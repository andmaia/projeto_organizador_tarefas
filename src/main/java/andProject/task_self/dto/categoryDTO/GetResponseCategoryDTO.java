package andProject.task_self.dto.categoryDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetResponseCategoryDTO {

    private Long Id;
    private String name;
    private String description;
    private LocalDateTime creation;
}
