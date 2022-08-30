package pl.mlisowski.projectmanagement.administration.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class RegisterCredentialsDto {

    String username;
    String email;
    String password;

}
