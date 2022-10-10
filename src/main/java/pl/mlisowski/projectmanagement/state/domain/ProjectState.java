package pl.mlisowski.projectmanagement.state.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.mlisowski.projectmanagement.common.BaseEntity;
import pl.mlisowski.projectmanagement.task.domain.Task;
import pl.mlisowski.projectmanagement.project.domain.Project;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectState extends BaseEntity {

    @Column
    private String name;

    @Column
    @Builder.Default
    private boolean completed = false;

    @OneToMany(mappedBy = "projectState", cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Task> tasks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;

    public void setProject(Project p) {
        this.project = p;
        this.project.getStates().add(this);
    }

    public void addTask(Task t) {
        if (!this.tasks.contains(t)) {
            this.tasks.add(t);
            t.setProjectState(this);
        }
    }

}
