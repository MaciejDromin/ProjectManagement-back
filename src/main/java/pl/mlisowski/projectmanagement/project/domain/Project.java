package pl.mlisowski.projectmanagement.project.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.mlisowski.projectmanagement.common.BaseEntity;
import pl.mlisowski.projectmanagement.state.domain.ProjectState;
import pl.mlisowski.projectmanagement.group.domain.ProjectGroup;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
