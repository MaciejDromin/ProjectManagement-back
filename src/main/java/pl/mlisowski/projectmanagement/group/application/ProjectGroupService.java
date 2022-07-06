package pl.mlisowski.projectmanagement.group.application;

import pl.mlisowski.projectmanagement.group.domain.ProjectGroup;
import pl.mlisowski.projectmanagement.group.domain.dto.ProjectGroupDto;

import java.util.List;

public interface ProjectGroupService {

    ProjectGroup saveProjectGroup(ProjectGroupDto projectGroup);

    List<ProjectGroup> getAll();

}
