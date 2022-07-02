package pl.mlisowski.projectManagement.hours.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mlisowski.projectManagement.hours.domain.Hours;

@Repository
public interface HoursRepository extends JpaRepository<Hours, Long> {

    Hours findHoursByOwnerId(Long ownerId);

}
