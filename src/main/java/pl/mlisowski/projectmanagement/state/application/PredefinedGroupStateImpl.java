package pl.mlisowski.projectmanagement.state.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.group.application.ProjectGroupService;
import pl.mlisowski.projectmanagement.state.application.port.PredefinedGroupStateRepository;
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState;
import pl.mlisowski.projectmanagement.state.domain.factory.PredefinedGroupStateFactory;
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredefinedGroupStateImpl implements PredefinedGroupStateService {

    private final PredefinedGroupStateRepository predefinedGroupStateRepository;
    private final PredefinedGroupStateFactory predefinedGroupStateFactory;
    private final ProjectGroupService projectGroupService;

    @Override
    public PredefinedGroupStateDto saveState(PredefinedGroupStateDto predefinedGroupState) {
        return predefinedGroupStateFactory.to(
                predefinedGroupStateRepository.save(predefinedGroupStateFactory.from(predefinedGroupState)));
    }

    @Override
    public PredefinedGroupStateDto saveStateInGroup(Long groupId, PredefinedGroupStateDto predefinedGroupStateDto) {
        var predefinedGroupState = predefinedGroupStateFactory.from(predefinedGroupStateDto);
        predefinedGroupState.setGroup(projectGroupService.getById(groupId));
        return predefinedGroupStateFactory.to(predefinedGroupStateRepository.save(predefinedGroupState));
    }

    @Override
    public PredefinedGroupState getById(Long predefinedGroupStateId) {
        return predefinedGroupStateRepository.findById(predefinedGroupStateId)
                .orElseThrow(() -> new EntityNotFoundException("Predefined Group State with ID %d doesn't exists!"
                        .formatted(predefinedGroupStateId)));
    }

    @Override
    public List<PredefinedGroupStateDto> getAll() {
        return predefinedGroupStateRepository.findAll().stream()
                .map(predefinedGroupStateFactory::to)
                .toList();
    }
}
