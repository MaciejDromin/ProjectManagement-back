package pl.mlisowski.projectmanagement.state.domain;

import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.state.domain.dto.ProjectStateDto;

@Component
public class ProjectStateFactory implements AbstractFactory<ProjectStateDto, ProjectState> {

    @Override
    public ProjectState from(ProjectStateDto toConvert) {
        return ProjectState.builder()
                .name(toConvert.getName())
                .completed(toConvert.isCompleted())
                .tasks(toConvert.getTasks())
                .project(toConvert.getProject())
                .build();
    }

    @Override
    public ProjectStateDto to(ProjectState toConvert) {
        return ProjectStateDto.builder()
                .name(toConvert.getName())
                .completed(toConvert.isCompleted())
                .tasks(toConvert.getTasks())
                .project(toConvert.getProject())
                .build();
    }

}
