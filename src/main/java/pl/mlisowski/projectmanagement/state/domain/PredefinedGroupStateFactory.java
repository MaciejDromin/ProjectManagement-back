package pl.mlisowski.projectmanagement.state.domain;

import org.springframework.stereotype.Component;
import pl.mlisowski.projectmanagement.common.AbstractFactory;
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto;

@Component
public class PredefinedGroupStateFactory implements AbstractFactory<PredefinedGroupStateDto, PredefinedGroupState> {
    @Override
    public PredefinedGroupState from(PredefinedGroupStateDto toConvert) {
        return PredefinedGroupState.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .tasks(toConvert.getTasks())
                .group(toConvert.getGroup())
                .build();
    }

    @Override
    public PredefinedGroupStateDto to(PredefinedGroupState toConvert) {
        return PredefinedGroupStateDto.builder()
                .id(toConvert.getId())
                .uuid(toConvert.getUuid())
                .name(toConvert.getName())
                .tasks(toConvert.getTasks())
                .group(toConvert.getGroup())
                .build();
    }
}
