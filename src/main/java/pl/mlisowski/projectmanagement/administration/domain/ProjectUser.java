package pl.mlisowski.projectmanagement.administration.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.mlisowski.projectmanagement.administration.domain.enums.Role;
import pl.mlisowski.projectmanagement.common.BaseEntity;
import pl.mlisowski.projectmanagement.group.domain.ProjectGroup;
import pl.mlisowski.projectmanagement.group.domain.SharedGroup;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(indexes = {
        @Index(name = "index1", columnList = "ID"),
        @Index(name = "index2", columnList = "EMAIL")
})
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectUser extends BaseEntity implements UserDetails {

    @Column
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    @Builder.Default
    private boolean enabled = false;

    @OneToOne(mappedBy = "projectUser")
    private VerificationToken verificationtoken;

    @OneToOne(mappedBy = "projectUser")
    private RecoveryToken recoveryToken;

    @OneToMany(mappedBy = "projectUser", cascade = CascadeType.ALL)
    @JsonManagedReference
    @Builder.Default
    private List<ProjectGroup> userGroups = new ArrayList<>();

    @OneToMany(mappedBy = "userShared")
    @JsonManagedReference
    @Builder.Default
    private Set<SharedGroup> sharedGroups = new HashSet<>();

    @OneToMany(mappedBy = "userSharing")
    @JsonManagedReference
    @Builder.Default
    private Set<SharedGroup> groupsShared = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(this.role.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
