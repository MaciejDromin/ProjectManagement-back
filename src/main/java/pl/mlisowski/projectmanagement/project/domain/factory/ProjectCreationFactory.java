package pl.mlisowski.projectmanagement.project.domain.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.group.application.ProjectGroupService;
import pl.mlisowski.projectmanagement.group.domain.ProjectGroup;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectCreationDto;

@Component
@RequiredArgsConstructor
public class ProjectCreationFactory implements AbstractFactory<ProjectCreationDto, Project> {

    private final ProjectGroupService projectGroupService;

    @Override
    public Project from(ProjectCreationDto toConvert) {
        final ProjectGroup projectGroup = projectGroupService.getById(toConvert.getGroupId());
        return Project.builder()
                .name(toConvert.getName())
                .description(toConvert.getDescription())
                .group(projectGroup)
                .build();
    }

    @Override
    public ProjectCreationDto to(Project toConvert) {
        throw new AssertionError();
    }

}
