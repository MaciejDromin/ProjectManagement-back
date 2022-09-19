package pl.mlisowski.projectmanagement.group.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.projectmanagement.group.domain.ProjectGroup;

import java.util.List;

public interface ProjectGroupRepository extends JpaRepository<ProjectGroup, Long> {

    List<ProjectGroup> findAllByProjectUserId(Long id);

}
