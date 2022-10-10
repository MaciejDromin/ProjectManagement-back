package pl.mlisowski.projectmanagement.group.application.port;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mlisowski.projectmanagement.group.domain.SharedGroup;

import java.util.List;

@Repository
public interface SharedGroupRepository extends JpaRepository<SharedGroup, Long> {

    List<SharedGroup> findAllByUserSharedId(Long userId);

}
