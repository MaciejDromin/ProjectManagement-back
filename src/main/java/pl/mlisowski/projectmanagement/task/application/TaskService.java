package pl.mlisowski.projectmanagement.task.application;

import pl.mlisowski.projectmanagement.task.domain.Task;
import pl.mlisowski.projectmanagement.task.domain.dto.TaskDto;
import pl.mlisowski.projectmanagement.task.domain.dto.TaskWithHoursDto;

import java.util.List;

public interface TaskService {

    TaskDto saveTask(TaskDto task);
    TaskDto saveTaskInPredefinedGroupState(Long predefinedGroupStateId, TaskDto taskDto);

    Task saveTaskWithHours(TaskWithHoursDto task);

    List<TaskDto> getAll();

}
