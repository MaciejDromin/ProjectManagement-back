package pl.mlisowski.projectManagement.task.domain;

import lombok.*;
import pl.mlisowski.projectManagement.common.BaseEntity;
import pl.mlisowski.projectManagement.state.domain.PredefinedGroupState;
import pl.mlisowski.projectManagement.state.domain.ProjectState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private boolean finished;

    @ManyToOne
    @JoinColumn(name = "predefined_group_state_id")
    private PredefinedGroupState predefinedGroupState;

    @ManyToOne
    @JoinColumn(name = "project_state_id")
    private ProjectState projectState;

}
