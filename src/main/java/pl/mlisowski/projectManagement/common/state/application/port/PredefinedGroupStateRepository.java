package pl.mlisowski.projectManagement.common.state.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.projectManagement.common.state.domain.PredefinedGroupState;

public interface PredefinedGroupStateRepository extends JpaRepository<PredefinedGroupState, Long> {
}
