package pl.mlisowski.projectmanagement.state.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.mlisowski.projectmanagement.common.BaseEntity;
import pl.mlisowski.projectmanagement.task.domain.Task;
import pl.mlisowski.projectmanagement.group.domain.ProjectGroup;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
