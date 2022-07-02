package pl.mlisowski.projectManagement.hours.application;

import pl.mlisowski.projectManagement.hours.domain.Hours;

public interface HoursService {

    Hours saveHours(Hours hours);

    Hours getByOwnerId(Long ownerId);

}
