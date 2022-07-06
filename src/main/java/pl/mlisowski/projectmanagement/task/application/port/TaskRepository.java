package pl.mlisowski.projectmanagement.task.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mlisowski.projectmanagement.task.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
