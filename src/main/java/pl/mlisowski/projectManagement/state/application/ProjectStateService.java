package pl.mlisowski.projectManagement.state.application;

import pl.mlisowski.projectManagement.state.domain.ProjectState;

import java.util.List;

public interface ProjectStateService {

    ProjectState saveProjectState(ProjectState projectState);

    List<ProjectState> getAll();

}
