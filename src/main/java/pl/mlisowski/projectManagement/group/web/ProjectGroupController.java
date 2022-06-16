package pl.mlisowski.projectManagement.group.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.projectManagement.group.application.ProjectGroupService;
import pl.mlisowski.projectManagement.group.domain.ProjectGroup;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class ProjectGroupController {

    private final ProjectGroupService projectGroupService;

    @GetMapping
    public List<ProjectGroup> getAllGroups() {
        return projectGroupService.getAll();
    }

    @PostMapping
    public ProjectGroup addProjectGroup(@RequestBody ProjectGroup projectGroup) {
        return projectGroupService.saveProjectGroup(projectGroup);
    }

}
