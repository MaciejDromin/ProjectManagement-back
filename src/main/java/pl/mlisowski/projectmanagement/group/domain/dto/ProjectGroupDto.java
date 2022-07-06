package pl.mlisowski.projectmanagement.group.domain.dto;

import lombok.Builder;
import lombok.Value;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState;
import java.util.Set;

@Value
@Builder
public class ProjectGroupDto {

    Long id;
    String uuid;
    String name;
    Set<Project> projects;
    Set<PredefinedGroupState> predefinedGroupStates;

}
