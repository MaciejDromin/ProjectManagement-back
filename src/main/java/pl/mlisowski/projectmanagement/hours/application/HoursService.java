package pl.mlisowski.projectmanagement.hours.application;

import pl.mlisowski.projectmanagement.hours.domain.Hours;

public interface HoursService {

    Hours saveHours(Hours hours);

    Hours getByOwnerId(String ownerId);

    Hours createHoursForOwnerId(String ownerId, int realHours, int estimatedHours);

    void deleteHoursByOwnerId(String ownerId);

}
