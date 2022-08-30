package pl.mlisowski.projectmanagement.administration.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mlisowski.projectmanagement.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecoveryToken extends BaseEntity {

    private String token;
    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private ProjectUser projectUser;
    private LocalDateTime expiryDate;

    private LocalDateTime calculateExpiryDate(int expiryTime){
        return LocalDateTime.now().plusMinutes(expiryTime);
    }

}
