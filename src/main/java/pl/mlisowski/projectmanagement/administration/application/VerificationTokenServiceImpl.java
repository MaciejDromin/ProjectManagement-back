package pl.mlisowski.projectmanagement.administration.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.administration.application.port.VerificationTokenRepository;
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;
import pl.mlisowski.projectmanagement.administration.domain.VerificationToken;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public void createVerificationToken(ProjectUser user, String token) {
        VerificationToken tk = new VerificationToken(token, user);
        verificationTokenRepository.save(tk);
    }

    @Override
    public VerificationToken findToken(String verificationToken) {
        VerificationToken tk = null;
        var token = verificationTokenRepository.findByToken(verificationToken);
        if (token.isPresent()) {
            tk = token.get();
        }
        return tk;
    }

}
