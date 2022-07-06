package pl.mlisowski.projectmanagement.state.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.projectmanagement.state.domain.ProjectState;

public interface ProjectStateRepository extends JpaRepository<ProjectState, Long> {
}
