package pl.mlisowski.projectmanagement.administration.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.administration.domain.dto.ProjectUserDto;
import pl.mlisowski.projectmanagement.common.AbstractFactory;

@Component
@RequiredArgsConstructor
public class ProjectUserFactory implements AbstractFactory<ProjectUserDto, ProjectUser> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public ProjectUser from(ProjectUserDto toConvert) {
        return ProjectUser.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .username(toConvert.getUsername())
                .password(passwordEncoder.encode(toConvert.getPassword()))
                .email(toConvert.getEmail())
                .enabled(toConvert.isEnabled())
                .role(toConvert.getRole())
                .build();
    }

    @Override
    public ProjectUserDto to(ProjectUser toConvert) {
        return ProjectUserDto.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .username(toConvert.getUsername())
                .email(toConvert.getEmail())
                .enabled(toConvert.isEnabled())
                .role(toConvert.getRole())
                .build();
    }
}
