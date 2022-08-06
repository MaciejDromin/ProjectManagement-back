package pl.mlisowski.projectmanagement.state.domain.factory;

import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState;
import pl.mlisowski.projectmanagement.state.domain.ProjectState;
import pl.mlisowski.projectmanagement.task.domain.Task;

import java.util.List;

@Component
public class PredefinedGroupStateToProjectStateFactory implements AbstractFactory<PredefinedGroupState, ProjectState> {

    @Override
    public ProjectState from(PredefinedGroupState toConvert) {
        ProjectState ps = new ProjectState();
        ps.setName(toConvert.getName());

        for (var taskToConvert : toConvert.getTasks()) {
            var task = createNewTask(taskToConvert);
            ps.addTask(task);
        }

        return ps;
    }

    @Override
    public PredefinedGroupState to(ProjectState toConvert) {
        throw new AssertionError();
    }

    private Task createNewTask(Task t) {
        return Task.builder()
                .name(t.getName())
                .description(t.getDescription())
                .finished(false)
                .predefinedGroupState(t.getPredefinedGroupState())
                .build();
    }
}
