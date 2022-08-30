package pl.mlisowski.projectmanagement.administration.domain;

import org.springframework.beans.factory.annotation.Value;

public interface UserDetailsProjection {

    @Value("#{target.id}")
    Long getId();

    @Value("#{target.username}")
    String getName();

    @Value("#{target.email}")
    String getEmail();

}
