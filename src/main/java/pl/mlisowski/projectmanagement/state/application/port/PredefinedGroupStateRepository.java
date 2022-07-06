package pl.mlisowski.projectmanagement.state.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState;

public interface PredefinedGroupStateRepository extends JpaRepository<PredefinedGroupState, Long> {
}
