package pl.mlisowski.projectManagement.common.state.application;

import pl.mlisowski.projectManagement.common.state.domain.PredefinedGroupState;

import java.util.List;

public interface PredefinedGroupStateService {

    PredefinedGroupState saveState(PredefinedGroupState predefinedGroupState);

    List<PredefinedGroupState> getAll();

}
