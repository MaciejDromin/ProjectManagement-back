package pl.mlisowski.projectmanagement.administration.application;

import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;
import pl.mlisowski.projectmanagement.administration.domain.RecoveryToken;
import pl.mlisowski.projectmanagement.administration.domain.dto.RecoveryCredentialsDto;

public interface RecoveryTokenService {

    void createToken(String email);
    RecoveryToken findToken(String recoveryToken);
    void createRecoveryToken(ProjectUser user, String token);
    void recoverPassword(RecoveryCredentialsDto recoveryCredentials);

}
