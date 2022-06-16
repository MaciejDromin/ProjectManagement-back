package pl.mlisowski.projectManagement.common.state.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectManagement.common.state.application.port.PredefinedGroupStateRepository;
import pl.mlisowski.projectManagement.common.state.domain.PredefinedGroupState;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PredefinedGroupStateImpl implements PredefinedGroupStateService {

    private final PredefinedGroupStateRepository predefinedGroupStateRepository;

    @Override
    public PredefinedGroupState saveState(PredefinedGroupState predefinedGroupState) {
        return predefinedGroupStateRepository.save(predefinedGroupState);
    }

    @Override
    public List<PredefinedGroupState> getAll() {
        return predefinedGroupStateRepository.findAll();
    }
}
