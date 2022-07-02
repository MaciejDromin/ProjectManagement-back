package pl.mlisowski.projectManagement.state.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.projectManagement.state.application.PredefinedGroupStateService;
import pl.mlisowski.projectManagement.state.domain.PredefinedGroupState;

import java.util.List;

@RestController
@RequestMapping("/predefinedGroupState")
@RequiredArgsConstructor
public class PredefinedGroupStateController {

    private final PredefinedGroupStateService predefinedGroupStateService;

    @GetMapping
    public List<PredefinedGroupState> getAllPredefinedGroupStates() {
        return predefinedGroupStateService.getAll();
    }

    @PostMapping
    public PredefinedGroupState savePredefinedGroupState(@RequestBody PredefinedGroupState predefinedGroupState) {
        return predefinedGroupStateService.saveState(predefinedGroupState);
    }

}
