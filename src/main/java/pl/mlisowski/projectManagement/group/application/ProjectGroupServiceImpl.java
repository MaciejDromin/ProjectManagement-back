package pl.mlisowski.projectManagement.group.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectManagement.group.application.port.ProjectGroupRepository;
import pl.mlisowski.projectManagement.group.domain.ProjectGroup;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectGroupServiceImpl implements ProjectGroupService {

    private final ProjectGroupRepository projectGroupRepository;

    @Override
    public ProjectGroup saveProjectGroup(ProjectGroup projectGroup) {
        return projectGroupRepository.save(projectGroup);
    }

    @Override
    public List<ProjectGroup> getAll() {
        return projectGroupRepository.findAll();
    }
}
