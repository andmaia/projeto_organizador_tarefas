package andProject.task_self.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="t_pts_task")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Nullable
    private String descricao;
    private LocalDateTime criacao;
    @Nullable
    private LocalDateTime termino;
    private boolean ativo;
    private boolean favorito;

    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Category categoria;

}
