package andProject.task_self.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name="t_pts_category")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Nullable
    private String descricao;
    private LocalDateTime cricao;
    @Nullable
    private LocalDateTime termino;
    private boolean ativo;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "categoria",fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<Task>();


}
