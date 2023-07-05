package andProject.task_self.dto.personDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetResponsePersonDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private  String email;
}
