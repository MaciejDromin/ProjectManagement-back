package pl.mlisowski.projectmanagement.administration.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.administration.application.VerificationTokenService;
import pl.mlisowski.projectmanagement.administration.domain.mail.MailSender;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final MessageSource messages;
    private final MailSender mailSender;
    private final VerificationTokenService verificationTokenService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        ProjectUser user = event.getUser();
        String token = UUID.randomUUID().toString();
        verificationTokenService.createVerificationToken(user, token);

        String userEmail = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "http://localhost:8080/registration-confirm?token=" + token;
        String message = "Your registration was successful, please finish the registration process by clicking following link:";
        mailSender.sendMail(subject, message +"\r\n" + confirmationUrl, userEmail);
    }

}
