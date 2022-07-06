package pl.mlisowski.projectmanagement.project.domain;

import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;

@Component
public class ProjectFactory implements AbstractFactory<ProjectDto, Project> {

    @Override
    public Project from(ProjectDto toConvert) {
        return Project.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .description(toConvert.getDescription())
                .status(toConvert.getStatus())
                .parentProject(toConvert.getParentProject())
                .childProjects(toConvert.getChildProjects())
                .group(toConvert.getGroup())
                .states(toConvert.getStates())
                .build();
    }

    @Override
    public ProjectDto to(Project toConvert) {
        return ProjectDto.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .description(toConvert.getDescription())
                .status(toConvert.getStatus())
                .parentProject(toConvert.getParentProject())
                .childProjects(toConvert.getChildProjects())
                .group(toConvert.getGroup())
                .states(toConvert.getStates())
                .build();
    }

}
