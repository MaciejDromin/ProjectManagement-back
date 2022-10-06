package pl.mlisowski.projectmanagement.state.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.hours.application.HoursService;
import pl.mlisowski.projectmanagement.state.application.port.ProjectStateRepository;
import pl.mlisowski.projectmanagement.state.domain.ProjectState;
import pl.mlisowski.projectmanagement.state.domain.factory.ProjectStateFactory;
import pl.mlisowski.projectmanagement.state.domain.dto.ProjectStateDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectStateServiceImpl implements ProjectStateService {

    private final ProjectStateRepository projectStateRepository;
    private final HoursService hoursService;
    private final ProjectStateFactory projectStateFactory;

    @Override
    public ProjectStateDto saveProjectState(ProjectStateDto projectState) {
        ProjectState savedProjectState = projectStateRepository.save(projectStateFactory.from(projectState));

        hoursService.createHoursForOwnerId(savedProjectState.getUuid(), 0, 0);

        return projectStateFactory.to(savedProjectState);
    }

    @Override
    public ProjectState updateProjectState(ProjectState projectState) {
        return projectStateRepository.save(projectState);
    }

    @Override
    public List<ProjectStateDto> getAll() {
        return projectStateRepository.findAll().stream()
                .map(projectStateFactory::to)
                .toList();
    }
}
