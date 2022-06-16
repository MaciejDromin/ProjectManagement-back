package pl.mlisowski.projectManagement.group.application;

import pl.mlisowski.projectManagement.group.domain.ProjectGroup;

import java.util.List;

public interface ProjectGroupService {

    ProjectGroup saveProjectGroup(ProjectGroup projectGroup);

    List<ProjectGroup> getAll();

}
