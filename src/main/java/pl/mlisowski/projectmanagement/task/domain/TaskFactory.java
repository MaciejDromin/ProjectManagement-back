package pl.mlisowski.projectmanagement.task.domain;

import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.task.domain.dto.TaskDto;

@Component
public class TaskFactory implements AbstractFactory<TaskDto, Task> {

    @Override
    public Task from(TaskDto toConvert) {
        return Task.builder()
                .name(toConvert.getName())
                .description(toConvert.getDescription())
                .finished(toConvert.isFinished())
                .predefinedGroupState(toConvert.getPredefinedGroupState())
                .projectState(toConvert.getProjectState())
                .build();
    }

    @Override
    public TaskDto to(Task toConvert) {
        return TaskDto.builder()
                .name(toConvert.getName())
                .description(toConvert.getDescription())
                .finished(toConvert.isFinished())
                .predefinedGroupState(toConvert.getPredefinedGroupState())
                .projectState(toConvert.getProjectState())
                .build();
    }
}
