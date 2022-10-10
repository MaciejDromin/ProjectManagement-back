package pl.mlisowski.projectmanagement.hours.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.mlisowski.projectmanagement.common.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Hours extends BaseEntity {

    @Column
    private String ownerId;

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
