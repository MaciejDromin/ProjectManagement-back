package pl.mlisowski.projectmanagement.task.domain.dto;

import lombok.Builder;
import lombok.Value;
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState;
import pl.mlisowski.projectmanagement.state.domain.ProjectState;

@Value
@Builder
public class TaskDto {

    Long id;
    String uuid;
    String name;
    String description;
    boolean finished;
    PredefinedGroupState predefinedGroupState;
    ProjectState projectState;

}
