package pl.mlisowski.projectmanagement.task.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto;
import pl.mlisowski.projectmanagement.state.domain.dto.ProjectStateDto;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TaskDto {

    @Builder
    public TaskDto(Long id, String uuid, String name, String description, boolean finished, PredefinedGroupStateDto predefinedGroupState, ProjectStateDto projectState) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.finished = finished;
        this.predefinedGroupState = predefinedGroupState;
        this.projectState = projectState;
    }

    Long id;
    String uuid = UUID.randomUUID().toString();
    String name;
    String description;
    boolean finished;
    PredefinedGroupStateDto predefinedGroupState;
    ProjectStateDto projectState;

}
