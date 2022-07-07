package pl.mlisowski.projectmanagement.state.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.group.domain.ProjectGroupFactory;
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto;
import pl.mlisowski.projectmanagement.task.domain.TaskFactory;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PredefinedGroupStateFactory implements AbstractFactory<PredefinedGroupStateDto, PredefinedGroupState> {

    private final TaskFactory taskFactory;
    private final ProjectGroupFactory projectGroupFactory;

    @Override
    public PredefinedGroupState from(PredefinedGroupStateDto toConvert) {
        if (toConvert == null) return null;
        return PredefinedGroupState.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .tasks(toConvert.getTasks().stream()
                        .map(taskFactory::from)
                        .collect(Collectors.toList())
                )
                .group(projectGroupFactory.from(toConvert.getGroup()))
                .build();
    }

    @Override
    public PredefinedGroupStateDto to(PredefinedGroupState toConvert) {
        if (toConvert == null) return null;
        return PredefinedGroupStateDto.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .tasks(toConvert.getTasks().stream()
                        .map(taskFactory::to)
                        .collect(Collectors.toList())
                )
                .group(projectGroupFactory.to(toConvert.getGroup()))
                .build();
    }

}
