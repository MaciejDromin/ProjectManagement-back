package pl.mlisowski.projectmanagement.task.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.mlisowski.projectmanagement.common.BaseEntity;
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState;
import pl.mlisowski.projectmanagement.state.domain.ProjectState;

import javax.persistence.*;

@SuperBuilder
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
    @JsonIgnore
    private PredefinedGroupState predefinedGroupState;

    @ManyToOne
    @JoinColumn(name = "project_state_id")
    @JsonIgnore
    private ProjectState projectState;

    public void setProjectState(ProjectState ps) {
        this.projectState = ps;
        this.projectState.addTask(this);
    }

}
