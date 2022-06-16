package pl.mlisowski.projectManagement.common.state.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mlisowski.projectManagement.common.BaseEntity;
import pl.mlisowski.projectManagement.common.Task;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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

}
