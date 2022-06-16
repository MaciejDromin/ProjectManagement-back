package pl.mlisowski.projectManagement.project.domain;

import lombok.*;
import pl.mlisowski.projectManagement.common.BaseEntity;

import javax.persistence.*;
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
    private Project parentProject;

    @OneToMany(mappedBy = "parentProject")
    private Set<Project> childProjects;

}
