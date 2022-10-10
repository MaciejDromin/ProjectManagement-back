package pl.mlisowski.projectmanagement.group.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.group.domain.dto.GroupsDto;
import pl.mlisowski.projectmanagement.group.domain.dto.ProjectGroupDto;
import pl.mlisowski.projectmanagement.project.domain.factory.ProjectFactory;
import pl.mlisowski.projectmanagement.state.domain.factory.PredefinedGroupStateFactory;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupsFactory {

    private final ProjectFactory projectFactory;
    private final PredefinedGroupStateFactory predefinedGroupStateFactory;

    public GroupsDto to(ProjectGroup toConvert, boolean isOwned) {
        if (toConvert == null) return null;
        return GroupsDto.builder()
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
                .isOwned(isOwned)
                .build();
    }

    public GroupsDto to(ProjectGroupDto toConvert, boolean isOwned) {
        if (toConvert == null) return null;
        return GroupsDto.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .projects(toConvert.getProjects())
                .predefinedGroupStates(toConvert.getPredefinedGroupStates())
                .isOwned(isOwned)
                .build();
    }

}
