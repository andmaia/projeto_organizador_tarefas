package andProject.task_self.dto.categoryDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCategoryDTO {
        @NotBlank(message = "Could not be null")
        private String name;
        private String description;
        @NotNull(message = "Could not be null")
        private Long person;
}

