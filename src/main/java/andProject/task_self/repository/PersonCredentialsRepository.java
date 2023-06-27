package andProject.task_self.repository;

import andProject.task_self.model.PersonCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonCredentialsRepository extends JpaRepository<PersonCredentials,Long> {
}
