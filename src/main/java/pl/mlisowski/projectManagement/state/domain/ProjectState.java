package pl.mlisowski.projectManagement.state.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mlisowski.projectManagement.common.BaseEntity;
import pl.mlisowski.projectManagement.task.domain.Task;
import pl.mlisowski.projectManagement.project.domain.Project;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectState extends BaseEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "projectState")
    @JsonIgnore
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;

}
