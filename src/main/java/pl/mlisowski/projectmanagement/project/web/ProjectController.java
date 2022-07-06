package pl.mlisowski.projectmanagement.project.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.projectmanagement.project.application.ProjectService;
import pl.mlisowski.projectmanagement.project.domain.NestProject;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;

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
    public Project saveProject(@RequestBody ProjectDto project) {
        return projectService.saveProject(project);
    }

    @PostMapping("/nestProject")
    public Project nestProject(@RequestBody NestProject nestProject) {
        return projectService.nestProject(nestProject);
    }

}
