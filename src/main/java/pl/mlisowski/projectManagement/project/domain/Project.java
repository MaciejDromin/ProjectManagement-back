package pl.mlisowski.projectManagement.project.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import pl.mlisowski.projectManagement.common.BaseEntity;
import pl.mlisowski.projectManagement.state.domain.ProjectState;
import pl.mlisowski.projectManagement.group.domain.ProjectGroup;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToOne
    @JoinColumn(name = "parent_project_id")
    @JsonBackReference
    private Project parentProject;

    @OneToMany(mappedBy = "parentProject", cascade = CascadeType.ALL)
    @JsonManagedReference
    @Builder.Default
    private Set<Project> childProjects = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private ProjectGroup group;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private Set<ProjectState> states;

}
