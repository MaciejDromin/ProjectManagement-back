package pl.mlisowski.projectmanagement.hours.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.hours.application.port.HoursRepository;
import pl.mlisowski.projectmanagement.hours.domain.Hours;

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

    @Override
    public Hours createHoursForOwnerId(Long ownerId, int realHours, int estimatedHours) {
        return hoursRepository.save(Hours.builder()
                .ownerId(ownerId)
                .realHours(realHours)
                .estimatedHours(estimatedHours)
                .build());
    }
}
