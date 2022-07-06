package pl.mlisowski.projectmanagement.group.domain;

import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.group.domain.dto.ProjectGroupDto;

@Component
public class ProjectGroupFactory implements AbstractFactory<ProjectGroupDto, ProjectGroup> {
    @Override
    public ProjectGroup from(ProjectGroupDto toConvert) {
        return ProjectGroup.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .projects(toConvert.getProjects())
                .predefinedGroupStates(toConvert.getPredefinedGroupStates())
                .build();
    }

    @Override
    public ProjectGroupDto to(ProjectGroup toConvert) {
        return ProjectGroupDto.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .projects(toConvert.getProjects())
                .predefinedGroupStates(toConvert.getPredefinedGroupStates())
                .build();
    }
}
