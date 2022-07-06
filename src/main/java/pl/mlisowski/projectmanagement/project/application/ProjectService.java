package pl.mlisowski.projectmanagement.project.application;


import pl.mlisowski.projectmanagement.project.domain.NestProject;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<Project> getAll();

    Project saveProject(ProjectDto project);

    Project updateProject(Project project);

    Project nestProject(NestProject nestProject);

}
