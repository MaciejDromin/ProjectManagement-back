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
    public Task saveTask(TaskDto task) {
        return taskRepository.save(taskFactory.from(task));
    }

    @Override
    public Task saveTaskInPredefinedGroupState(Long predefinedGroupStateId, TaskDto taskDto) {
        var task = taskFactory.from(taskDto);
        task.setPredefinedGroupState(predefinedGroupStateService.getById(predefinedGroupStateId));
        return taskRepository.save(task);
    }

    @Override
    public Task saveTaskWithHours(TaskWithHoursDto task) {
        Task savedTask = taskRepository.save(taskFactory.from(task.getTaskDto()));

        hoursService.createHoursForOwnerId(savedTask.getId(), task.getRealHours(), task.getEstimatedHours());

        return savedTask;
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }
}
