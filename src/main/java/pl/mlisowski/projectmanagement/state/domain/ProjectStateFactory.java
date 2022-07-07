package pl.mlisowski.projectmanagement.state.domain;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.project.domain.ProjectFactory;
import pl.mlisowski.projectmanagement.state.domain.dto.ProjectStateDto;
import pl.mlisowski.projectmanagement.task.domain.TaskFactory;
import java.util.stream.Collectors;

@Component
public class ProjectStateFactory implements AbstractFactory<ProjectStateDto, ProjectState> {

    @Setter
    private TaskFactory taskFactory;
    @Setter
    private ProjectFactory projectFactory;

    @Override
    public ProjectState from(ProjectStateDto toConvert) {
        if (toConvert == null) return null;
        return ProjectState.builder()
                .name(toConvert.getName())
                .completed(toConvert.isCompleted())
                .tasks(toConvert.getTasks().stream()
                        .map(taskFactory::from)
                        .collect(Collectors.toList())
                )
                .project(projectFactory.from(toConvert.getProject()))
                .build();
    }

    @Override
    public ProjectStateDto to(ProjectState toConvert) {
        if (toConvert == null) return null;
        return ProjectStateDto.builder()
                .name(toConvert.getName())
                .completed(toConvert.isCompleted())
                .tasks(toConvert.getTasks().stream()
                        .map(taskFactory::to)
                        .collect(Collectors.toList())
                )
                .project(projectFactory.to(toConvert.getProject()))
                .build();
    }

}
