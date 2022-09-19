package pl.mlisowski.projectmanagement.group.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ProjectGroupDto {

    @Builder
    public ProjectGroupDto(Long id, String uuid, String name, Set<ProjectDto> projects,
                           Set<PredefinedGroupStateDto> predefinedGroupStates) {
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
