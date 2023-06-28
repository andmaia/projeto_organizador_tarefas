package andProject.task_self.dto.taskDTO;

import andProject.task_self.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDTO {

    @NotNull(message = "Could not be null")
    private String name;
    private String description;

    @NotNull(message = "Could not be null.")
    private Long category;

}
