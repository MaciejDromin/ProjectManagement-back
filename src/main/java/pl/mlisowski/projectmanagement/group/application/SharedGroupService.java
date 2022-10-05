package pl.mlisowski.projectmanagement.group.application;

import pl.mlisowski.projectmanagement.group.domain.SharedGroup;
import pl.mlisowski.projectmanagement.group.domain.dto.GroupsDto;
import pl.mlisowski.projectmanagement.group.domain.dto.ShareGroupDto;

import java.util.List;

public interface SharedGroupService {

    List<SharedGroup> findAllByUserId(Long userId);

    List<GroupsDto> getAllWithShared(Long userId);

    void shareGroup(ShareGroupDto shareGroupDto);

}
