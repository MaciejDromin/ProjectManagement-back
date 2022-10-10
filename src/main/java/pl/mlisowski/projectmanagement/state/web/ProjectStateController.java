package pl.mlisowski.projectmanagement.state.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.projectmanagement.state.application.ProjectStateService;
import pl.mlisowski.projectmanagement.state.domain.dto.ProjectStateDto;
import java.util.List;

@RestController
@RequestMapping("/projectstates")
@RequiredArgsConstructor
public class ProjectStateController {

    private final ProjectStateService projectStateService;

    @GetMapping
    public List<ProjectStateDto> getAllProjectStates() {
        return projectStateService.getAll();
    }

    @PostMapping
    public ProjectStateDto saveProjectState(@RequestBody ProjectStateDto projectState) {
        return projectStateService.saveProjectState(projectState);
    }

}
