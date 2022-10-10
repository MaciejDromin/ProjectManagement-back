package pl.mlisowski.projectmanagement.project.application;


import pl.mlisowski.projectmanagement.project.domain.NestProjectDto;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectCreationDto;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> getAll();

    List<ProjectDto> getAllProjectsInGroup(Long groupId);

    ProjectDto saveProject(ProjectCreationDto project);

    ProjectDto getProjectById(Long projectId);

    Project updateProject(Project project);

    ProjectDto nestProject(NestProjectDto nestProjectDto);

    void deleteProject(Long id);

}
