package pl.mlisowski.projectmanagement.administration.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.projectmanagement.administration.application.ProjectUserService;
import pl.mlisowski.projectmanagement.administration.application.RecoveryTokenService;
import pl.mlisowski.projectmanagement.administration.application.VerificationTokenService;
import pl.mlisowski.projectmanagement.administration.domain.EmailDto;
import pl.mlisowski.projectmanagement.administration.domain.OnRegistrationCompleteEvent;
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;
import pl.mlisowski.projectmanagement.administration.domain.dto.ProjectUserDto;
import pl.mlisowski.projectmanagement.administration.domain.dto.RecoveryCredentialsDto;
import pl.mlisowski.projectmanagement.administration.domain.dto.RegisterCredentialsDto;
import pl.mlisowski.projectmanagement.administration.domain.dto.UserDetailsDto;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ProjectUserController {

    private final ProjectUserService projectUserService;
    private final ApplicationEventPublisher eventPublisher;
    private final VerificationTokenService verificationTokenService;
    private final RecoveryTokenService recoveryTokenService;

    @GetMapping("/me")
    public UserDetailsDto getUserDetails() {
        return projectUserService.getUserDetails();
    }

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody RegisterCredentialsDto registerCredentials, HttpServletRequest request) {
        ProjectUser temp = new ProjectUser();
        temp.setEmail(registerCredentials.getEmail());
        temp.setPassword(registerCredentials.getPassword());
        if (projectUserService.userExists(temp)) {
            throw new DataIntegrityViolationException("User with such credentials already exists!");
        }
        ProjectUser user = projectUserService.saveProjectUser(ProjectUserDto.builder()
                .username(registerCredentials.getUsername())
                .email(registerCredentials.getEmail())
                .password(registerCredentials.getPassword())
                .build()
        );
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale()));
    }

    @GetMapping("/registration-confirm")
    public void confirmRegistration(@RequestParam String token) {
        var tk = verificationTokenService.findToken(token);
        var user = tk.getProjectUser();
        user.setEnabled(true);
        projectUserService.updateProjectUser(user);
    }

    @PostMapping("/password-recovery")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void passwordRecovery(@RequestBody EmailDto email) {
        if (projectUserService.findProjectUserByEmail(email.getEmail()).isEmpty()) {
            throw new EntityNotFoundException("User with email %s doesn't exists!".formatted(email.getEmail()));
        }

        recoveryTokenService.createToken(email.getEmail());
    }

    @PostMapping("/recovery-confirm")
    public void recoveryConfirm(@RequestBody RecoveryCredentialsDto recoveryCredentialsDto) {
        recoveryTokenService.recoverPassword(recoveryCredentialsDto);
    }

}
