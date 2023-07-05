package andProject.task_self.repository;

import andProject.task_self.model.PersonCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface PersonCredentialsRepository extends JpaRepository<PersonCredentials,Long> {
    UserDetails findByEmail(String username);

    boolean existsByEmail(String email);
}
