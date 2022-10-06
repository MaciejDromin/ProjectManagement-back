package pl.mlisowski.projectmanagement.group.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.projectmanagement.group.application.ProjectGroupService;
import pl.mlisowski.projectmanagement.group.application.SharedGroupService;
import pl.mlisowski.projectmanagement.group.domain.dto.GroupsDto;
import pl.mlisowski.projectmanagement.group.domain.dto.ProjectGroupDto;
import pl.mlisowski.projectmanagement.group.domain.dto.ShareGroupDto;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class ProjectGroupController {

    private final ProjectGroupService projectGroupService;
    private final SharedGroupService sharedGroupService;

    @GetMapping
    public List<ProjectGroupDto> getAllGroups() {
        return projectGroupService.getAll();
    }

    @GetMapping("/users/{userId}/shared")
    public List<GroupsDto> getAllGroupsWithShared(@PathVariable Long userId) {
        return sharedGroupService.getAllWithShared(userId);
    }

    @PostMapping("/share")
    public void shareGroup(@RequestBody ShareGroupDto shareGroupDto) {
        sharedGroupService.shareGroup(shareGroupDto);
    }

    @GetMapping("/users/{userId}")
    public List<ProjectGroupDto> getAllGroupsPerUser(@PathVariable Long userId) {
        return projectGroupService.getAllByProjectUserId(userId);
    }

    @PostMapping("/users/{userId}")
    public ProjectGroupDto addProjectGroupForUser(@PathVariable Long userId, @RequestBody ProjectGroupDto projectGroup) {
        return projectGroupService.saveProjectGroupForUser(userId, projectGroup);
    }

}
