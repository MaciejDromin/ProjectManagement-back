package pl.mlisowski.projectmanagement.project.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.projectmanagement.project.application.ProjectService;
import pl.mlisowski.projectmanagement.project.domain.NestProject;
import pl.mlisowski.projectmanagement.project.domain.Project;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectCreationDto;
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAll();
    }

    @GetMapping("/groups/{groupId}")
    public List<Project> getAllProjectsInGroup(@PathVariable Long groupId) {
        return projectService.getAllProjectsInGroup(groupId);
    }

    @GetMapping("/{projectId}")
    public ProjectDto getProjectById(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProjectDto saveProject(@RequestBody ProjectCreationDto project) {
        return projectService.saveProject(project);
    }

    @PostMapping("/nestProject")
    public Project nestProject(@RequestBody NestProject nestProject) {
        return projectService.nestProject(nestProject);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }

}
