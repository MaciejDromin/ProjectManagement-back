package pl.mlisowski.projectmanagement.administration.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.administration.application.port.ProjectUserRepository;
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;
import pl.mlisowski.projectmanagement.administration.domain.ProjectUserFactory;
import pl.mlisowski.projectmanagement.administration.domain.UserDetailsProjection;
import pl.mlisowski.projectmanagement.administration.domain.dto.ProjectUserDto;
import pl.mlisowski.projectmanagement.administration.domain.dto.UserDetailsDto;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectUserServiceImpl implements ProjectUserService {

    private final ProjectUserRepository projectUserRepository;
    private final ProjectUserFactory projectUserFactory;

    @Override
    public ProjectUser saveProjectUser(ProjectUserDto projectUser) {
        return projectUserRepository.save(projectUserFactory.from(projectUser));
    }

    @Override
    public ProjectUser updateProjectUser(ProjectUser projectUser) {
        return projectUserRepository.save(projectUser);
    }

    @Override
    public ProjectUser getProjectUserByEmail(String email) {
        return projectUserRepository.findProjectUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: %s doesn't exists!".formatted(email)));
    }

    @Override
    public Optional<ProjectUser> findProjectUserByEmail(String email) {
        return projectUserRepository.findProjectUserByEmail(email);
    }

    @Override
    public ProjectUser getProjectUserByUsername(String username) {
        return projectUserRepository.findProjectUserByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User with name: %s doesn't exists!".formatted(username)));
    }

    @Override
    public ProjectUser getProjectUserById(Long id) {
        return projectUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: %s doesn't exists!".formatted(id)));
    }

    @Override
    public UserDetailsDto getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return from(projectUserRepository.getProjectUserByUsername(authentication.getName()));
    }

    @Override
    public boolean userExists(ProjectUser user) {
        var userTemp = projectUserRepository.findProjectUserByEmail(user.getEmail());
        if (userTemp.isPresent()) return true;
        userTemp = projectUserRepository.findProjectUserByUsername(user.getUsername());
        return userTemp.isPresent();
    }

    private UserDetailsDto from(UserDetailsProjection userDetailsProjection) {
        return UserDetailsDto.builder()
                .id(userDetailsProjection.getId())
                .name(userDetailsProjection.getName())
                .email(userDetailsProjection.getEmail())
                .build();
    }

}
