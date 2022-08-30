package pl.mlisowski.projectmanagement.administration.domain;

import lombok.Value;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Value
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    ProjectUser user;
    Locale locale;

}
