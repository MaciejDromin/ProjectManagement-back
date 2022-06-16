package pl.mlisowski.projectManagement.project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.projectManagement.project.application.ProjectService;
import pl.mlisowski.projectManagement.project.domain.Project;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAll();
    }

    @PostMapping
    public Project saveProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

}
