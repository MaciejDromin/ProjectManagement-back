package pl.mlisowski.projectmanagement.group.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;
import pl.mlisowski.projectmanagement.common.BaseEntity;
import javax.persistence.*;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SharedGroup extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "shared_group_id")
    @JsonBackReference
    private ProjectGroup groupShared;

    @ManyToOne
    @JoinColumn(name = "user_shared_id")
    @JsonBackReference
    private ProjectUser userShared;

    @ManyToOne
    @JoinColumn(name = "user_sharing_id")
    @JsonBackReference
    private ProjectUser userSharing;

}
