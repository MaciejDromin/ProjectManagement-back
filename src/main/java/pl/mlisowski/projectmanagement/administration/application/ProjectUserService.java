package pl.mlisowski.projectmanagement.administration.application;

import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;
import pl.mlisowski.projectmanagement.administration.domain.dto.ProjectUserDto;
import pl.mlisowski.projectmanagement.administration.domain.dto.UserDetailsDto;

import java.util.Optional;

public interface ProjectUserService {

    ProjectUser saveProjectUser(ProjectUserDto projectUser);

    ProjectUser updateProjectUser(ProjectUser projectUser);

    ProjectUser getProjectUserByEmail(String email);

    Optional<ProjectUser> findProjectUserByEmail(String email);

    ProjectUser getProjectUserByUsername(String username);

    ProjectUser getProjectUserById(Long id);

    UserDetailsDto getUserDetails();

    boolean userExists(ProjectUser user);

}
