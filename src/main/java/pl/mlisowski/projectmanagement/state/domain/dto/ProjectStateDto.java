package pl.mlisowski.projectmanagement.state.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;
import pl.mlisowski.projectmanagement.task.domain.dto.TaskDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProjectStateDto {

    @Builder
    public ProjectStateDto(Long id, String uuid, String name, boolean completed, List<TaskDto> tasks, ProjectDto project) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.completed = completed;
        this.tasks = tasks;
        this.project = project;
    }

    Long id;
    String uuid = UUID.randomUUID().toString();
    String name;
    boolean completed;
    List<TaskDto> tasks = new ArrayList<>();
    ProjectDto project;

}
