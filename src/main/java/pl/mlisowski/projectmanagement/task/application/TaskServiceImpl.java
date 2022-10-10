package pl.mlisowski.projectmanagement.task.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.hours.application.HoursService;
import pl.mlisowski.projectmanagement.state.application.PredefinedGroupStateService;
import pl.mlisowski.projectmanagement.task.application.port.TaskRepository;
import pl.mlisowski.projectmanagement.task.domain.Task;
import pl.mlisowski.projectmanagement.task.domain.TaskFactory;
import pl.mlisowski.projectmanagement.task.domain.dto.TaskDto;
import pl.mlisowski.projectmanagement.task.domain.dto.TaskWithHoursDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final HoursService hoursService;
    private final TaskFactory taskFactory;
    private final PredefinedGroupStateService predefinedGroupStateService;

    @Override
    public TaskDto saveTask(TaskDto task) {
        return taskFactory.to(taskRepository.save(taskFactory.from(task)));
    }

    @Override
    public TaskDto saveTaskInPredefinedGroupState(Long predefinedGroupStateId, TaskDto taskDto) {
        var task = taskFactory.from(taskDto);
        task.setPredefinedGroupState(predefinedGroupStateService.getById(predefinedGroupStateId));
        return taskFactory.to(taskRepository.save(task));
    }

    @Override
    public TaskDto saveTaskWithHours(TaskWithHoursDto task) {
        Task savedTask = taskRepository.save(taskFactory.from(task.getTaskDto()));

        hoursService.createHoursForOwnerId(savedTask.getUuid(), task.getRealHours(), task.getEstimatedHours());

        return taskFactory.to(savedTask);
    }

    @Override
    public List<TaskDto> getAll() {
        return taskRepository.findAll().stream()
                .map(taskFactory::to)
                .toList();
    }
}
