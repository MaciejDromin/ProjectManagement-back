package pl.mlisowski.projectmanagement.task.domain;

import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.task.domain.dto.TaskDto;

@Component
public class TaskFactory implements AbstractFactory<TaskDto, Task> {

    @Override
    public Task from(TaskDto toConvert) {
        if (toConvert == null) return null;
        return Task.builder()
                .name(toConvert.getName())
                .description(toConvert.getDescription())
                .finished(toConvert.isFinished())
                .build();
    }

    @Override
    public TaskDto to(Task toConvert) {
        if (toConvert == null) return null;
        return TaskDto.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .description(toConvert.getDescription())
                .finished(toConvert.isFinished())
                .build();
    }
}
