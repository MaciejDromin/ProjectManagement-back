package pl.mlisowski.projectmanagement.administration.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Value
@Builder
public class RegisterCredentialsDto {

    String username;
    String email;
    String password;

}
