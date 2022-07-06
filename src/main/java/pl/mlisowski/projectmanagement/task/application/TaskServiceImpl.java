package pl.mlisowski.projectmanagement.task.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.hours.application.HoursService;
import pl.mlisowski.projectmanagement.task.application.port.TaskRepository;
import pl.mlisowski.projectmanagement.task.domain.Task;
import pl.mlisowski.projectmanagement.task.domain.TaskFactory;
import pl.mlisowski.projectmanagement.task.domain.dto.TaskWithHoursDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final HoursService hoursService;
    private final TaskFactory taskFactory;

    @Override
    public Task saveTask(TaskWithHoursDto task) {
        Task savedTask = taskRepository.save(taskFactory.from(task.getTaskDto()));

        hoursService.createHoursForOwnerId(savedTask.getId(), task.getRealHours(), task.getEstimatedHours());

        return savedTask;
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }
}
