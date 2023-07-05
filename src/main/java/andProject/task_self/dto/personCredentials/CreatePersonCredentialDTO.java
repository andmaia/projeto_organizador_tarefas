package andProject.task_self.dto.personCredentials;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonCredentialDTO {
    @NotNull(message = "Could not be null.")
    private String email;
    @NotNull(message = "Could not be null.")
    private String password;


}
