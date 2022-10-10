package pl.mlisowski.projectmanagement.state.domain.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.state.domain.ProjectState;
import pl.mlisowski.projectmanagement.state.domain.dto.ProjectStateDto;
import pl.mlisowski.projectmanagement.task.domain.TaskFactory;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectStateFactory implements AbstractFactory<ProjectStateDto, ProjectState> {

    private final TaskFactory taskFactory;

    @Override
    public ProjectState from(ProjectStateDto toConvert) {
        if (toConvert == null) return null;
        return ProjectState.builder()
                .name(toConvert.getName())
                .completed(toConvert.isCompleted())
                .tasks(toConvert.getTasks().stream()
                        .map(taskFactory::from)
                        .collect(Collectors.toCollection(ArrayList::new))
                )
                .build();
    }

    @Override
    public ProjectStateDto to(ProjectState toConvert) {
        if (toConvert == null) return null;
        return ProjectStateDto.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .completed(toConvert.isCompleted())
                .tasks(toConvert.getTasks().stream()
                        .map(taskFactory::to)
                        .toList()
                )
                .build();
    }

}
