package pl.mlisowski.projectmanagement.administration.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.mlisowski.projectmanagement.administration.domain.enums.Role;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ProjectUserDto {

    @Builder
    public ProjectUserDto(Long id, String uuid, String username, String email, String password, Role role, boolean enabled) {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    Long id;
    String uuid = UUID.randomUUID().toString();
    String username;
    String email;
    String password;
    Role role;
    boolean enabled = false;

}
