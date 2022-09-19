package pl.mlisowski.projectmanagement.administration.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;
import pl.mlisowski.projectmanagement.administration.domain.RecoveryToken;

import java.util.Optional;

@Repository
public interface RecoveryTokenRepository extends JpaRepository<RecoveryToken, Long> {

    Optional<RecoveryToken> findByToken(String token);
    Optional<RecoveryToken> findByProjectUser(ProjectUser user);

}
