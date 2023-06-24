package andProject.task_self.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="t_pts_person_credencials")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String senha;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_pessoa")
    @MapsId
    private Person person;
}
