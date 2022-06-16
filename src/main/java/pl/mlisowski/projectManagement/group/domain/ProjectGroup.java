package pl.mlisowski.projectManagement.group.domain;

import lombok.*;
import pl.mlisowski.projectManagement.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectGroup extends BaseEntity {

    @Column
    private String name;

}
