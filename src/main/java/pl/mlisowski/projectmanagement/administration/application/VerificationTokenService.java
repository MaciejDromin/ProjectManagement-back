package pl.mlisowski.projectmanagement.administration.application;

import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;
import pl.mlisowski.projectmanagement.administration.domain.VerificationToken;

public interface VerificationTokenService {

    void createVerificationToken(ProjectUser user, String token);
    VerificationToken findToken(String verificationToken);

}
