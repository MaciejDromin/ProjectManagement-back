package pl.mlisowski.projectmanagement.administration.domain.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RecoveryCredentialsDto {

    String token;
    String password;

}
