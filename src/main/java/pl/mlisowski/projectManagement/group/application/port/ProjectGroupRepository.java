package pl.mlisowski.projectManagement.group.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.projectManagement.group.domain.ProjectGroup;

public interface ProjectGroupRepository extends JpaRepository<ProjectGroup, Long> {
}
