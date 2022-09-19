package pl.mlisowski.projectmanagement.task.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.projectmanagement.task.application.TaskService;
import pl.mlisowski.projectmanagement.task.domain.Task;
import pl.mlisowski.projectmanagement.task.domain.dto.TaskDto;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAll();
    }

    @PostMapping
    public Task saveTask(@RequestBody TaskDto taskDto) {
        return taskService.saveTask(taskDto);
    }

    @PostMapping("/predefinedgroupstates/{predefinedgroupstateId}")
    public Task savePredefinedGroupStateForUserInGroup(@PathVariable Long predefinedgroupstateId,
                                                          @RequestBody TaskDto task) {
        return taskService.saveTaskInPredefinedGroupState(predefinedgroupstateId, task);
    }

}
