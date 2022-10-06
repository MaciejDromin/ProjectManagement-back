package pl.mlisowski.projectmanagement.group.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;
import pl.mlisowski.projectmanagement.common.BaseEntity;
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState;
import pl.mlisowski.projectmanagement.project.domain.Project;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectGroup extends BaseEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "group")
    @JsonManagedReference
    @Builder.Default
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "group")
    @JsonManagedReference
    @Builder.Default
    private Set<PredefinedGroupState> predefinedGroupStates = new HashSet<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_user_id")
    @JsonBackReference
    private ProjectUser projectUser;

    @OneToMany(mappedBy = "groupShared")
    @JsonManagedReference
    @Builder.Default
    private Set<SharedGroup> sharedGroups = new HashSet<>();

}
