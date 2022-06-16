package pl.mlisowski.projectManagement.common.state.application;

import pl.mlisowski.projectManagement.common.state.domain.ProjectState;

import java.util.List;

public interface ProjectStateService {

    ProjectState saveProjectState(ProjectState projectState);

    List<ProjectState> getAll();

}
