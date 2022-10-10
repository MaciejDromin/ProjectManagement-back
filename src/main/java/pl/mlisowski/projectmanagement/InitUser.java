package pl.mlisowski.projectmanagement;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.administration.application.ProjectUserService;
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;
import pl.mlisowski.projectmanagement.administration.domain.dto.ProjectUserDto;
import pl.mlisowski.projectmanagement.administration.domain.enums.Role;

@Component
@RequiredArgsConstructor
public class InitUser implements CommandLineRunner {
    private final ProjectUserService projectUserService;

    @Override
    public void run(String... args) throws Exception {
        if (projectUserService.findProjectUserByEmail("test@test.com").isEmpty()) {
            ProjectUser u = projectUserService.saveProjectUser(ProjectUserDto.builder()
                    .username("Test")
                    .email("test@test.com")
                    .password("test123")
                    .role(Role.ADMIN)
                    .build());
            u.setEnabled(true);
            projectUserService.updateProjectUser(u);
        }
        if (projectUserService.findProjectUserByEmail("test2@test.com").isEmpty()) {
            ProjectUser u = projectUserService.saveProjectUser(ProjectUserDto.builder()
                    .username("Test2")
                    .email("test2@test.com")
                    .password("test123")
                    .role(Role.ADMIN)
                    .build());
            u.setEnabled(true);
            projectUserService.updateProjectUser(u);
        }
    }

}
