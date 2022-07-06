package pl.mlisowski.projectmanagement.state.domain.dto;

import lombok.Builder;
import lombok.Value;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.task.domain.Task;
import java.util.List;

@Value
@Builder
public class ProjectStateDto {

    String name;
    boolean completed;
    List<Task> tasks;
    Project project;

}
