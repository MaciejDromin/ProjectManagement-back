package pl.mlisowski.projectManagement.state.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.projectManagement.state.domain.PredefinedGroupState;

public interface PredefinedGroupStateRepository extends JpaRepository<PredefinedGroupState, Long> {
}
