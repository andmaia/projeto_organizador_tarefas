package andProject.task_self.dto.personDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePersonDTO {
    @NotNull(message = "Id could not be null")
    private Long id;
    private String name;
    private LocalDate birthDate;
}
