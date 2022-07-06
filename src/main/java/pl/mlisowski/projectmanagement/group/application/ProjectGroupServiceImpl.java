package pl.mlisowski.projectmanagement.group.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.group.application.port.ProjectGroupRepository;
import pl.mlisowski.projectmanagement.group.domain.ProjectGroup;
import pl.mlisowski.projectmanagement.group.domain.ProjectGroupFactory;
import pl.mlisowski.projectmanagement.group.domain.dto.ProjectGroupDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectGroupServiceImpl implements ProjectGroupService {

    private final ProjectGroupRepository projectGroupRepository;
    private final ProjectGroupFactory projectGroupFactory;

    @Override
    public ProjectGroup saveProjectGroup(ProjectGroupDto projectGroup) {
        return projectGroupRepository.save(projectGroupFactory.from(projectGroup));
    }

    @Override
    public List<ProjectGroup> getAll() {
        return projectGroupRepository.findAll();
    }
}
