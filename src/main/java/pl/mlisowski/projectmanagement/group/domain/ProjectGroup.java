package pl.mlisowski.projectmanagement.group.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.mlisowski.projectmanagement.common.BaseEntity;
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState;
import pl.mlisowski.projectmanagement.project.domain.Project;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
    private Set<Project> projects;

    @OneToMany(mappedBy = "group")
    @JsonManagedReference
    private Set<PredefinedGroupState> predefinedGroupStates;

}
