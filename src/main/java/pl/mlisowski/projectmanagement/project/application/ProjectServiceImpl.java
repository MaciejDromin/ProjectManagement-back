package pl.mlisowski.projectmanagement.project.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.administration.application.ProjectUserService;
import pl.mlisowski.projectmanagement.hours.application.HoursService;
import pl.mlisowski.projectmanagement.project.application.port.ProjectRepository;
import pl.mlisowski.projectmanagement.project.domain.NestProject;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;
import pl.mlisowski.projectmanagement.project.domain.factory.ProjectCreationFactory;
import pl.mlisowski.projectmanagement.project.domain.factory.ProjectFactory;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectCreationDto;
import pl.mlisowski.projectmanagement.state.domain.factory.PredefinedGroupStateToProjectStateFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final HoursService hoursService;
    private final ProjectFactory projectFactory;
    private final ProjectCreationFactory projectCreationFactory;
    private final PredefinedGroupStateToProjectStateFactory predefinedGroupStateToProjectStateFactory;
    private final ProjectUserService projectUserService;


    @Override
    public List<ProjectDto> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = projectUserService.getProjectUserByUsername(authentication.getName());
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

        hoursService.createHoursForOwnerId(savedProject.getId(), 0, 0);

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
    public ProjectDto nestProject(NestProject nestProject) {
        Project nestTo = nestProject.getNestTo();
        Project nested = nestProject.getNested();

        nested.setParentProject(nestTo);
        nestTo.getChildProjects().add(nested);

        return projectFactory.to(projectRepository.save(nestTo));
    }

    @Override
    @Transactional
    public void deleteProject(Long id) {
        hoursService.deleteHoursByOwnerId(id);
        projectRepository.delete(projectRepository.getReferenceById(id));
    }

}
