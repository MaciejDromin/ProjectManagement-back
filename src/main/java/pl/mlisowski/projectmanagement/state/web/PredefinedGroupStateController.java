package pl.mlisowski.projectmanagement.state.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mlisowski.projectmanagement.state.application.PredefinedGroupStateService;
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState;
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto;

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
    public PredefinedGroupState savePredefinedGroupState(@RequestBody PredefinedGroupStateDto predefinedGroupState) {
        return predefinedGroupStateService.saveState(predefinedGroupState);
    }

}
