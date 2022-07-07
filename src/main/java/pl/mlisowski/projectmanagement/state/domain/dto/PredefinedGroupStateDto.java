package pl.mlisowski.projectmanagement.state.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import pl.mlisowski.projectmanagement.group.domain.dto.ProjectGroupDto;
import pl.mlisowski.projectmanagement.task.domain.dto.TaskDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PredefinedGroupStateDto {

    @Builder
    public PredefinedGroupStateDto(Long id, String uuid, String name, List<TaskDto> tasks, ProjectGroupDto group) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.tasks = tasks;
        this.group = group;
    }

    Long id;
    String uuid = UUID.randomUUID().toString();
    String name;
    List<TaskDto> tasks = new ArrayList<>();
    ProjectGroupDto group;

}
