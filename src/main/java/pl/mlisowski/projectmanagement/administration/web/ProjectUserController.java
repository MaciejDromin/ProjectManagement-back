package pl.mlisowski.projectmanagement.administration.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mlisowski.projectmanagement.administration.application.ProjectUserService;
import pl.mlisowski.projectmanagement.administration.domain.dto.UserDetailsDto;

@RestController
@RequiredArgsConstructor
public class ProjectUserController {

    private final ProjectUserService projectUserService;

    @GetMapping("/me")
    public UserDetailsDto getUserDetails() {
        return projectUserService.getUserDetails();
    }

}
