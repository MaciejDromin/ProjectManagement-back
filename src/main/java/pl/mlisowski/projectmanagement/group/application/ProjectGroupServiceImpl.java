package pl.mlisowski.projectmanagement.group.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.administration.application.ProjectUserService;
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
    private final ProjectUserService projectUserService;

    @Override
    public ProjectGroupDto saveProjectGroup(ProjectGroupDto projectGroup) {
        return projectGroupFactory.to(projectGroupRepository.save(projectGroupFactory.from(projectGroup)));
    }

    @Override
    public ProjectGroupDto saveProjectGroupForUser(Long userId, ProjectGroupDto projectGroup) {
        var group = projectGroupFactory.from(projectGroup);
        group.setProjectUser(projectUserService.getProjectUserById(userId));
        return projectGroupFactory.to(projectGroupRepository.save(group));
    }

    @Override
    public List<ProjectGroupDto> getAll() {
        return projectGroupRepository.findAll().stream()
                .map(projectGroupFactory::to)
                .toList();
    }

    @Override
    public List<ProjectGroupDto> getAllByProjectUserId(Long userId) {
        return projectGroupRepository.findAllByProjectUserId(userId).stream()
                .map(projectGroupFactory::to)
                .toList();
    }

    @Override
    public ProjectGroup getById(Long id) {
        return projectGroupRepository.findById(id).orElseGet(null);
    }
}
