package pl.mlisowski.projectmanagement.task.domain;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupStateFactory;
import pl.mlisowski.projectmanagement.state.domain.ProjectStateFactory;
import pl.mlisowski.projectmanagement.task.domain.dto.TaskDto;

@Component
@RequiredArgsConstructor
public class TaskFactory implements AbstractFactory<TaskDto, Task> {

    @Setter
    private PredefinedGroupStateFactory predefinedGroupStateFactory;
    private final ProjectStateFactory projectStateFactory;

    @Override
    public Task from(TaskDto toConvert) {
        if (toConvert == null) return null;
        return Task.builder()
                .name(toConvert.getName())
                .description(toConvert.getDescription())
                .finished(toConvert.isFinished())
                .predefinedGroupState(predefinedGroupStateFactory.from(toConvert.getPredefinedGroupState()))
                .projectState(projectStateFactory.from(toConvert.getProjectState()))
                .build();
    }

    @Override
    public TaskDto to(Task toConvert) {
        if (toConvert == null) return null;
        return TaskDto.builder()
                .name(toConvert.getName())
                .description(toConvert.getDescription())
                .finished(toConvert.isFinished())
                .predefinedGroupState(predefinedGroupStateFactory.to(toConvert.getPredefinedGroupState()))
                .projectState(projectStateFactory.to(toConvert.getProjectState()))
                .build();
    }
}
