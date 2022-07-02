package pl.mlisowski.projectManagement.state.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.projectManagement.state.domain.ProjectState;

public interface ProjectStateRepository extends JpaRepository<ProjectState, Long> {
}
