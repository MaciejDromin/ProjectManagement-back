package pl.mlisowski.projectmanagement.state.application;

import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState;
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto;

import java.util.List;

public interface PredefinedGroupStateService {

    PredefinedGroupState saveState(PredefinedGroupStateDto predefinedGroupState);

    PredefinedGroupState saveStateInGroup(Long groupId, PredefinedGroupStateDto predefinedGroupStateDto);
    PredefinedGroupState getById(Long predefinedGroupStateId);

    List<PredefinedGroupState> getAll();

}
