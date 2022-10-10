package pl.mlisowski.projectmanagement.administration.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.administration.application.port.RecoveryTokenRepository;
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;
import pl.mlisowski.projectmanagement.administration.domain.RecoveryToken;
import pl.mlisowski.projectmanagement.administration.domain.dto.RecoveryCredentialsDto;
import pl.mlisowski.projectmanagement.administration.domain.mail.MailSender;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecoveryTokenServiceImpl implements RecoveryTokenService {

    private final RecoveryTokenRepository recoveryTokenRepository;
    private final ProjectUserService projectUserService;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createToken(String email) {
        var userOpt = projectUserService.findProjectUserByEmail(email);
        if (userOpt.isEmpty()) throw new EntityNotFoundException("User with email %s doens't exists!".formatted(email));
        var user = userOpt.get();
        String token = UUID.randomUUID().toString();
        createRecoveryToken(user, token);
        sendRecoveryMail(email, token);
    }

    @Override
    public RecoveryToken findToken(String recoveryToken) {
        RecoveryToken tk = null;
        var token = recoveryTokenRepository.findByToken(recoveryToken);
        if (token.isPresent()) {
            tk = token.get();
        }
        return tk;
    }

    @Override
    public void createRecoveryToken(ProjectUser user, String token) {
        var tk = new RecoveryToken(token, user, LocalDateTime.now().plusHours(2));
        recoveryTokenRepository.save(tk);
    }

    @Override
    public void recoverPassword(RecoveryCredentialsDto recoveryCredentials) {
        RecoveryToken tk = findToken(recoveryCredentials.getToken());
        ProjectUser user = tk.getProjectUser();
        user.setPassword(passwordEncoder.encode(recoveryCredentials.getPassword()));
        projectUserService.updateProjectUser(user);
    }

    private void sendRecoveryMail(String email, String token) {
        String subject = "Password Recovery";
        String confirmationUrl = "http://localhost:8080/recovery-confirm?token=" + token;
        String message = "To finish your password recovery click on the link below:";
        mailSender.sendMail(subject, message +"\r\n" + confirmationUrl, email);
    }

}
