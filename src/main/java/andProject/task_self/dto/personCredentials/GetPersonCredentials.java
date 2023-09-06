package andProject.task_self.dto.personCredentials;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPersonCredentials {
    private Long id;
   private String email;
   private  String password;
}
