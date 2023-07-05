package andProject.task_self.dto.taskDTO;

import andProject.task_self.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTaskDTO {
    private Long id;
    private String name;
    private String description;
    private Long category;
    private LocalDateTime creation;
    private boolean favorite;

}
