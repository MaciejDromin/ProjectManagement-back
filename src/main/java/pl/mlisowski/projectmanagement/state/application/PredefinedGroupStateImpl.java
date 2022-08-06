package pl.mlisowski.projectmanagement.state.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.state.application.port.PredefinedGroupStateRepository;
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState;
import pl.mlisowski.projectmanagement.state.domain.factory.PredefinedGroupStateFactory;
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PredefinedGroupStateImpl implements PredefinedGroupStateService {

    private final PredefinedGroupStateRepository predefinedGroupStateRepository;
    private final PredefinedGroupStateFactory predefinedGroupStateFactory;

    @Override
    public PredefinedGroupState saveState(PredefinedGroupStateDto predefinedGroupState) {
        return predefinedGroupStateRepository.save(predefinedGroupStateFactory.from(predefinedGroupState));
    }

    @Override
    public List<PredefinedGroupState> getAll() {
        return predefinedGroupStateRepository.findAll();
    }
}
