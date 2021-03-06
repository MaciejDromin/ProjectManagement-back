package pl.mlisowski.projectmanagement.hours.application;

import pl.mlisowski.projectmanagement.hours.domain.Hours;

public interface HoursService {

    Hours saveHours(Hours hours);

    Hours getByOwnerId(Long ownerId);

    Hours createHoursForOwnerId(Long ownerId, int realHours, int estimatedHours);

}
