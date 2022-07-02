package pl.mlisowski.projectManagement.group.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import pl.mlisowski.projectManagement.common.BaseEntity;
import pl.mlisowski.projectManagement.state.domain.PredefinedGroupState;
import pl.mlisowski.projectManagement.project.domain.Project;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectGroup extends BaseEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "group")
    @JsonManagedReference
    private Set<Project> projects;

    @OneToMany(mappedBy = "group")
    @JsonManagedReference
    private Set<PredefinedGroupState> predefinedGroupStates;

}
