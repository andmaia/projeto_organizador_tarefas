package andProject.task_self.dto.personDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonDTO {
        @NotBlank(message = "Could not be null.")
        private String name;

        @NotNull(message = "Could not be null")
        private LocalDate birthDate;
}
