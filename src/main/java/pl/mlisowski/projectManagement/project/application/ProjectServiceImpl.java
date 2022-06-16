package pl.mlisowski.projectManagement.project.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectManagement.project.application.port.ProjectRepository;
import pl.mlisowski.projectManagement.project.domain.Project;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;


    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }
}
