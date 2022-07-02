package pl.mlisowski.projectManagement.hours.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectManagement.hours.application.port.HoursRepository;
import pl.mlisowski.projectManagement.hours.domain.Hours;

@Service
@RequiredArgsConstructor
public class HoursServiceImpl implements HoursService {

    private final HoursRepository hoursRepository;

    @Override
    public Hours saveHours(Hours hours) {
        return hoursRepository.save(hours);
    }

    @Override
    public Hours getByOwnerId(Long ownerId) {
        return hoursRepository.findHoursByOwnerId(ownerId);
    }
}
