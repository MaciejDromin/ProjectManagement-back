package pl.mlisowski.projectManagement.project.application;


import pl.mlisowski.projectManagement.project.domain.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAll();

    Project saveProject(Project project);

}
