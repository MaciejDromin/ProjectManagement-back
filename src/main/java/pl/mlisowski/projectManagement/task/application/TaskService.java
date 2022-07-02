package pl.mlisowski.projectManagement.task.application;

import pl.mlisowski.projectManagement.task.domain.Task;

import java.util.List;

public interface TaskService {

    Task saveTask(Task task);

    List<Task> getAll();

}
