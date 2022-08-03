package pl.mlisowski.projectmanagement.hours.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mlisowski.projectmanagement.hours.domain.Hours;

@Repository
public interface HoursRepository extends JpaRepository<Hours, Long> {

    Hours findHoursByOwnerId(Long ownerId);

    void deleteHoursByOwnerId(Long ownerId);

}
