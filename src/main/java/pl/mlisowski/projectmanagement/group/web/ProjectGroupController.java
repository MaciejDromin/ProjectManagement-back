package pl.mlisowski.projectmanagement.group.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.projectmanagement.group.application.ProjectGroupService;
import pl.mlisowski.projectmanagement.group.domain.ProjectGroup;
import pl.mlisowski.projectmanagement.group.domain.dto.ProjectGroupDto;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class ProjectGroupController {

    private final ProjectGroupService projectGroupService;

    @GetMapping
    public List<ProjectGroup> getAllGroups() {
        return projectGroupService.getAll();
    }

    @GetMapping("/users/{userId}")
    public List<ProjectGroup> getAllGroupsPerUser(@PathVariable Long userId) {
        return projectGroupService.getAllByProjectUserId(userId);
    }

    @PostMapping("/users/{userId}")
    public ProjectGroup addProjectGroupForUser(@PathVariable Long userId, @RequestBody ProjectGroupDto projectGroup) {
        return projectGroupService.saveProjectGroupForUser(userId, projectGroup);
    }

    @PostMapping
    public ProjectGroup addProjectGroup(@RequestBody ProjectGroupDto projectGroup) {
        return projectGroupService.saveProjectGroup(projectGroup);
    }

}
