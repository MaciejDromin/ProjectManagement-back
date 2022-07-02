package pl.mlisowski.projectManagement.state.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mlisowski.projectManagement.common.BaseEntity;
import pl.mlisowski.projectManagement.task.domain.Task;
import pl.mlisowski.projectManagement.group.domain.ProjectGroup;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredefinedGroupState extends BaseEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "predefinedGroupState")
    @JsonIgnore
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private ProjectGroup group;

}
