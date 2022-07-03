package pl.mlisowski.projectManagement.calculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectManagement.project.application.ProjectService;
import pl.mlisowski.projectManagement.project.domain.Project;
import pl.mlisowski.projectManagement.project.domain.ProjectStatus;
import pl.mlisowski.projectManagement.state.application.ProjectStateService;
import pl.mlisowski.projectManagement.state.domain.ProjectState;
import pl.mlisowski.projectManagement.task.domain.Task;

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
                projectStateService.saveProjectState(state);
            }

            everythingFinished = everythingFinished && state.isCompleted();
        }

        for (Project nestedProject : project.getChildProjects()) {
            calculateAndUpdateCurrentState(nestedProject);
            everythingFinished = everythingFinished && (ProjectStatus.FINISHED.equals(nestedProject.getStatus()));
        }

        if (everythingFinished) {
            project.setStatus(ProjectStatus.FINISHED);
            projectService.saveProject(project);
        }

    }

}
