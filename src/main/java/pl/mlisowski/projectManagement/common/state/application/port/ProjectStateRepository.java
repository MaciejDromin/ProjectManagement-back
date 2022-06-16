package pl.mlisowski.projectManagement.common.state.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.projectManagement.common.state.domain.ProjectState;

public interface ProjectStateRepository extends JpaRepository<ProjectState, Long> {
}
