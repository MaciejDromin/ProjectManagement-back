package pl.mlisowski.projectmanagement.state.domain.dto;

import lombok.Builder;
import lombok.Value;
import pl.mlisowski.projectmanagement.group.domain.ProjectGroup;
import pl.mlisowski.projectmanagement.task.domain.Task;
import java.util.List;

@Value
@Builder
public class PredefinedGroupStateDto {

    Long id;
    String uuid;
    String name;
    List<Task> tasks;
    ProjectGroup group;

}
