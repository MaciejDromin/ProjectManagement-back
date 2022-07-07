package pl.mlisowski.projectmanagement.group.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProjectGroupDto {

    @Builder
    public ProjectGroupDto(Long id, String uuid, String name, Set<ProjectDto> projects, Set<PredefinedGroupStateDto> predefinedGroupStates) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.projects = projects;
        this.predefinedGroupStates = predefinedGroupStates;
    }

    Long id;
    String uuid = UUID.randomUUID().toString();
    String name;
    Set<ProjectDto> projects = new HashSet<>();
    Set<PredefinedGroupStateDto> predefinedGroupStates = new HashSet<>();

}
