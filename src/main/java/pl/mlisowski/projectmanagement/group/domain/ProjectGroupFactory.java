package pl.mlisowski.projectmanagement.group.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.group.domain.dto.ProjectGroupDto;
import pl.mlisowski.projectmanagement.project.domain.factory.ProjectFactory;
import pl.mlisowski.projectmanagement.state.domain.factory.PredefinedGroupStateFactory;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectGroupFactory implements AbstractFactory<ProjectGroupDto, ProjectGroup> {

    private final ProjectFactory projectFactory;
    private final PredefinedGroupStateFactory predefinedGroupStateFactory;

    @Override
    public ProjectGroup from(ProjectGroupDto toConvert) {
        if (toConvert == null) return null;
        return ProjectGroup.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .projects(toConvert.getProjects().stream()
                        .map(projectFactory::from)
                        .collect(Collectors.toSet())
                )
                .predefinedGroupStates(toConvert.getPredefinedGroupStates().stream()
                        .map(predefinedGroupStateFactory::from)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    @Override
    public ProjectGroupDto to(ProjectGroup toConvert) {
        if (toConvert == null) return null;
        return ProjectGroupDto.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .projects(toConvert.getProjects().stream()
                        .map(projectFactory::to)
                        .collect(Collectors.toSet())
                )
                .predefinedGroupStates(toConvert.getPredefinedGroupStates().stream()
                        .map(predefinedGroupStateFactory::to)
                        .collect(Collectors.toSet())
                )
                .build();
    }

}
