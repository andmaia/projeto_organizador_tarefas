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
    private String description;
    private LocalDateTime creation;
    @Nullable
    private LocalDateTime deadline;
    private boolean active;
    private boolean favorite;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
