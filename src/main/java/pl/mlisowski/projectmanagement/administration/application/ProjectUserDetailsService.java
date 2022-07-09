package pl.mlisowski.projectmanagement.administration.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser;

@Service
@RequiredArgsConstructor
public class ProjectUserDetailsService implements UserDetailsService {

    private final ProjectUserService projectUserService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ProjectUser user = projectUserService.getProjectUserByEmail(email);

        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true,
                true, true, user.getAuthorities());
    }

}
