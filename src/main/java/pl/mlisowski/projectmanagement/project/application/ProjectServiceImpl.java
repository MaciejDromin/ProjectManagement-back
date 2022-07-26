package pl.mlisowski.projectmanagement.project.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.hours.application.HoursService;
import pl.mlisowski.projectmanagement.project.application.port.ProjectRepository;
import pl.mlisowski.projectmanagement.project.domain.NestProject;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.project.domain.ProjectFactory;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final HoursService hoursService;
    private final ProjectFactory projectFactory;


    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project saveProject(ProjectDto project) {
        Project savedProject = projectRepository.save(projectFactory.from(project));

        hoursService.createHoursForOwnerId(savedProject.getId(), 0, 0);

        return savedProject;
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project nestProject(NestProject nestProject) {
        Project nestTo = nestProject.getNestTo();
        Project nested = nestProject.getNested();

        nested.setParentProject(nestTo);
        nestTo.getChildProjects().add(nested);

        return projectRepository.save(nestTo);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.delete(projectRepository.getReferenceById(id));
    }

}
