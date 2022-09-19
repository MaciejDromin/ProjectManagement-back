package pl.mlisowski.projectmanagement.project.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.projectmanagement.project.domain.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    //List<Project> findAllByProjectUserId(Long id);
    List<Project> findAllByGroupId(Long id);

}
