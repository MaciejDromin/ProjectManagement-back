package pl.mlisowski.projectmanagement.project.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import pl.mlisowski.projectmanagement.group.domain.dto.ProjectGroupDto;
import pl.mlisowski.projectmanagement.project.domain.ProjectStatus;
import pl.mlisowski.projectmanagement.state.domain.dto.ProjectStateDto;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProjectDto {

    @Builder
    public ProjectDto(Long id, String uuid, String name, String description, ProjectStatus status,
                      ProjectDto parentProject, Set<ProjectDto> childProjects, ProjectGroupDto group,
                      Set<ProjectStateDto> states) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.status = status;
        this.parentProject = parentProject;
        this.childProjects = childProjects;
        this.group = group;
        this.states = states;
    }

    Long id;
    String uuid = UUID.randomUUID().toString();
    String name;
    String description;
    ProjectStatus status;
    @JsonBackReference
    ProjectDto parentProject;
    @JsonManagedReference
    Set<ProjectDto> childProjects = new HashSet<>();
    ProjectGroupDto group;
    Set<ProjectStateDto> states = new HashSet<>();

}
