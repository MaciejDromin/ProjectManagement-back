package pl.mlisowski.projectmanagement.project.domain.dto;

import lombok.Builder;
import lombok.Value;
import pl.mlisowski.projectmanagement.group.domain.ProjectGroup;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.project.domain.ProjectStatus;
import pl.mlisowski.projectmanagement.state.domain.ProjectState;
import java.util.Set;

@Value
@Builder
public class ProjectDto {

    Long id;
    String uuid;
    String name;
    String description;
    ProjectStatus status;
    Project parentProject;
    Set<Project> childProjects;
    ProjectGroup group;
    Set<ProjectState> states;

}
