package pl.mlisowski.projectManagement.hours.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import pl.mlisowski.projectManagement.common.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hours extends BaseEntity {

    @Column
    private Long ownerId;

    @Column
    @Builder.Default
    private int realHours = 0;

    @Column
    @Builder.Default
    private int estimatedHours = 0;

    @OneToMany(mappedBy = "parentHours", cascade = CascadeType.ALL)
    @JsonManagedReference
    @Builder.Default
    private List<Hours> childHours = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_hours_id")
    @JsonBackReference
    private Hours parentHours;

}
