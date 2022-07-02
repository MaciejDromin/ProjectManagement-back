package pl.mlisowski.projectManagement.state.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectManagement.state.application.port.ProjectStateRepository;
import pl.mlisowski.projectManagement.state.domain.ProjectState;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectStateServiceImpl implements ProjectStateService {

    private final ProjectStateRepository projectStateRepository;

    @Override
    public ProjectState saveProjectState(ProjectState projectState) {
        return projectStateRepository.save(projectState);
    }

    @Override
    public List<ProjectState> getAll() {
        return projectStateRepository.findAll();
    }
}
