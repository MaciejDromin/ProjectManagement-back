package pl.mlisowski.projectmanagement.calculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.project.application.ProjectService;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.project.domain.ProjectStatus;
import pl.mlisowski.projectmanagement.state.application.ProjectStateService;
import pl.mlisowski.projectmanagement.state.domain.ProjectState;
import pl.mlisowski.projectmanagement.task.domain.Task;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StateCalculatorService {

    private final ProjectStateService projectStateService;
    private final ProjectService projectService;

    public void calculateCurrentStates(Project p) {
        Project top = p;
        while (top.getParentProject() != null) top = top.getParentProject();

        calculateAndUpdateCurrentState(top);
    }

    @Transactional
    private void calculateAndUpdateCurrentState(Project project) {
        boolean everythingFinished = true;

        for (ProjectState state : project.getStates()) {
            List<Task> unfinishedTasks = state.getTasks().stream()
                    .filter(task -> !task.isFinished())
                    .toList();
            if (unfinishedTasks.isEmpty() && !state.isCompleted()) {
                state.setCompleted(true);
                projectStateService.updateProjectState(state);
            }

            everythingFinished = everythingFinished && state.isCompleted();
        }

        for (Project nestedProject : project.getChildProjects()) {
            calculateAndUpdateCurrentState(nestedProject);
            everythingFinished = everythingFinished && (ProjectStatus.FINISHED.equals(nestedProject.getStatus()));
        }

        if (everythingFinished) {
            project.setStatus(ProjectStatus.FINISHED);
            projectService.updateProject(project);
        }

    }

}
