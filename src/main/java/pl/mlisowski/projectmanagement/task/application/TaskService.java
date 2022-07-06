package pl.mlisowski.projectmanagement.task.application;

import pl.mlisowski.projectmanagement.task.domain.Task;
import pl.mlisowski.projectmanagement.task.domain.dto.TaskWithHoursDto;

import java.util.List;

public interface TaskService {

    Task saveTask(TaskWithHoursDto task);

    List<Task> getAll();

}
