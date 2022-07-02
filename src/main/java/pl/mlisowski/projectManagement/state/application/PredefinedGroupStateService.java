package pl.mlisowski.projectManagement.state.application;

import pl.mlisowski.projectManagement.state.domain.PredefinedGroupState;

import java.util.List;

public interface PredefinedGroupStateService {

    PredefinedGroupState saveState(PredefinedGroupState predefinedGroupState);

    List<PredefinedGroupState> getAll();

}
