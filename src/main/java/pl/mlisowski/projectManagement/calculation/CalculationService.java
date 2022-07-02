package pl.mlisowski.projectManagement.calculation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectManagement.hours.application.HoursService;
import pl.mlisowski.projectManagement.hours.domain.Hours;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculationService {

    private Set<Hours> visited;
    private final HoursService hoursService;


    public void calculateRealHours(Hours hours) {
        visited = new HashSet<>();
        Hours top = hours;
        while (top.getParentHours() != null) top = top.getParentHours();

        int calculatedRealHours = calculateRealHoursRec(top);
        log.info("Calculated real hours {} for object with ID {}", calculatedRealHours, hours.getId());
    }

    @Transactional
    private int calculateRealHoursRec(Hours hours) {
        if (visited.contains(hours)) return hours.getRealHours();
        visited.add(hours);
        int realHours = 0;
        if (hours.getChildHours().isEmpty()) {
            return hours.getRealHours();
        }

        for(Hours h : hours.getChildHours()) {
            realHours += calculateRealHoursRec(h);
        }

        if (realHours != hours.getRealHours()) {
            hours.setRealHours(realHours);
            hoursService.saveHours(hours);
        }

        return realHours;
    }

    public void calculateEstimatedHours(Hours hours) {
        visited = new HashSet<>();
        Hours top = hours;
        while (top.getParentHours() != null) top = top.getParentHours();

        int calculatedEstimatedHours = calculateEstimatedHoursRec(top);
        log.info("Calculated estimated hours {} for object with ID {}", calculatedEstimatedHours, hours.getId());
    }

    @Transactional
    private int calculateEstimatedHoursRec(Hours hours) {
        if (visited.contains(hours)) return hours.getEstimatedHours();
        visited.add(hours);
        int estimatedHours = 0;
        if (hours.getChildHours().isEmpty()) {
            return hours.getEstimatedHours();
        }

        for(Hours h : hours.getChildHours()) {
            estimatedHours += calculateEstimatedHoursRec(h);
        }

        if (estimatedHours != hours.getEstimatedHours()) {
            hours.setEstimatedHours(estimatedHours);
            hoursService.saveHours(hours);
        }

        return estimatedHours;
    }

}
