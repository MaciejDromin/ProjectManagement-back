package pl.mlisowski.projectmanagement.project.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.projectmanagement.project.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
