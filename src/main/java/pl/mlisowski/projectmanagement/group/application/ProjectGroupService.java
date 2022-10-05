package pl.mlisowski.projectmanagement.group.application;

import pl.mlisowski.projectmanagement.group.domain.ProjectGroup;
import pl.mlisowski.projectmanagement.group.domain.dto.ProjectGroupDto;

import java.util.List;

public interface ProjectGroupService {

    ProjectGroupDto saveProjectGroup(ProjectGroupDto projectGroup);

    ProjectGroupDto saveProjectGroupForUser(Long userId, ProjectGroupDto projectGroup);

    List<ProjectGroupDto> getAll();

    List<ProjectGroupDto> getAllByProjectUserId(Long userId);

    ProjectGroup getById(Long id);

}
