package pl.mlisowski.projectmanagement.project.domain.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;
import pl.mlisowski.projectmanagement.state.domain.factory.ProjectStateFactory;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectFactory implements AbstractFactory<ProjectDto, Project> {

    private final ProjectStateFactory projectStateFactory;

    @Override
    public Project from(ProjectDto toConvert) {
        if (toConvert == null) return null;
        return Project.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .description(toConvert.getDescription())
                .status(toConvert.getStatus())
                .parentProject(from(toConvert.getParentProject()))
                .childProjects(toConvert.getChildProjects().stream()
                        .map(this::from)
                        .collect(Collectors.toSet())
                )
                .states(toConvert.getStates().stream()
                        .map(projectStateFactory::from)
                        .collect(Collectors.toSet())
                )
                .build();
    }

    @Override
    public ProjectDto to(Project toConvert) {
        if (toConvert == null) return null;
        return ProjectDto.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .description(toConvert.getDescription())
                .status(toConvert.getStatus())
                .childProjects(toConvert.getChildProjects().stream()
                        .map(this::to)
                        .collect(Collectors.toSet())
                )
                .states(toConvert.getStates().stream()
                        .map(projectStateFactory::to)
                        .collect(Collectors.toSet())
                )
                .build();
    }

}
