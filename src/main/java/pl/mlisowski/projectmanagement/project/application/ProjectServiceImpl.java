package pl.mlisowski.projectmanagement.project.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.hours.application.HoursService;
import pl.mlisowski.projectmanagement.project.application.port.ProjectRepository;
import pl.mlisowski.projectmanagement.project.domain.NestProjectDto;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;
import pl.mlisowski.projectmanagement.project.domain.factory.ProjectCreationFactory;
import pl.mlisowski.projectmanagement.project.domain.factory.ProjectFactory;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectCreationDto;
import pl.mlisowski.projectmanagement.state.domain.factory.PredefinedGroupStateToProjectStateFactory;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final HoursService hoursService;
    private final ProjectFactory projectFactory;
    private final ProjectCreationFactory projectCreationFactory;
    private final PredefinedGroupStateToProjectStateFactory predefinedGroupStateToProjectStateFactory;


    @Override
    public List<ProjectDto> getAll() {
        return projectRepository.findAll().stream()
                .map(projectFactory::to)
                .toList();
    }

    @Override
    public List<ProjectDto> getAllProjectsInGroup(Long groupId) {
        return projectRepository.findAllByGroupId(groupId).stream()
                .map(projectFactory::to)
                .toList();
    }

    @Override
    @Transactional
    public ProjectDto saveProject(ProjectCreationDto project) {
        Project projectToSave = projectCreationFactory.from(project);

        for (var pgs : projectToSave.getGroup().getPredefinedGroupStates()) {
            var ps = predefinedGroupStateToProjectStateFactory.from(pgs);
            projectToSave.addProjectState(ps);
        }

        Project savedProject = projectRepository.save(projectToSave);

        hoursService.createHoursForOwnerId(savedProject.getUuid(), 0, 0);

        return projectFactory.to(savedProject);
    }

    @Override
    public ProjectDto getProjectById(Long projectId) {
        return projectFactory.to(projectRepository.getReferenceById(projectId));
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public ProjectDto nestProject(NestProjectDto nestProjectDto) {
        Project nestTo = projectFactory.from(nestProjectDto.getNestTo());
        Project nested = projectFactory.from(nestProjectDto.getNested());

        nested.setParentProject(nestTo);
        nestTo.getChildProjects().add(nested);

        return projectFactory.to(projectRepository.save(nestTo));
    }

    @Override
    @Transactional
    public void deleteProject(Long id) {
        var project = projectRepository.getReferenceById(id);
        hoursService.deleteHoursByOwnerId(project.getUuid());
        projectRepository.delete(project);
    }

}
