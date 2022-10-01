package pl.mlisowski.projectmanagement.state.application;

import pl.mlisowski.projectmanagement.state.domain.ProjectState;
import pl.mlisowski.projectmanagement.state.domain.dto.ProjectStateDto;

import java.util.List;

public interface ProjectStateService {

    ProjectStateDto saveProjectState(ProjectStateDto projectState);

    ProjectState updateProjectState(ProjectState projectState);

    List<ProjectStateDto> getAll();

}
