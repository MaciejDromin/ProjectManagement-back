package pl.mlisowski.projectmanagement.administration.domain;

import lombok.Value;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Value
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    public OnRegistrationCompleteEvent(ProjectUser user, Locale locale){
        super(user);
        this.user = user;
        this.locale = locale;
    }

    ProjectUser user;
    Locale locale;

}
