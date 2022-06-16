package pl.mlisowski.projectManagement.project.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.projectManagement.project.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
