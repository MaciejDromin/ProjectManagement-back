package pl.mlisowski.projectmanagement.administration.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;
import pl.mlisowski.projectmanagement.administration.domain.UserDetailsProjection;

import java.util.Optional;

public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {

    Optional<ProjectUser> findProjectUserByUsername(String username);
    Optional<ProjectUser> findProjectUserByEmail(String email);

    UserDetailsProjection getProjectUserByUsername(String username);

}
