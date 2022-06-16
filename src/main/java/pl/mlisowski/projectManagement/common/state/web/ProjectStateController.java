package pl.mlisowski.projectManagement.common.state.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.projectManagement.common.state.application.ProjectStateService;
import pl.mlisowski.projectManagement.common.state.domain.ProjectState;

import java.util.List;

@RestController
@RequestMapping("/projectState")
@RequiredArgsConstructor
public class ProjectStateController {

    private final ProjectStateService projectStateService;

    @GetMapping
    public List<ProjectState> getAllProjectStates() {
        return projectStateService.getAll();
    }

    @PostMapping
    public ProjectState saveProjectState(@RequestBody ProjectState projectState) {
        return projectStateService.saveProjectState(projectState);
    }

}
