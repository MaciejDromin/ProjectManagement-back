package pl.mlisowski.projectmanagement.group.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mlisowski.projectmanagement.administration.application.ProjectUserService;
import pl.mlisowski.projectmanagement.group.application.port.SharedGroupRepository;
import pl.mlisowski.projectmanagement.group.domain.GroupsFactory;
import pl.mlisowski.projectmanagement.group.domain.SharedGroup;
import pl.mlisowski.projectmanagement.group.domain.dto.GroupsDto;
import pl.mlisowski.projectmanagement.group.domain.dto.ShareGroupDto;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SharedGroupServiceImpl implements SharedGroupService {

    private final SharedGroupRepository sharedGroupRepository;
    private final ProjectUserService projectUserService;
    private final ProjectGroupService projectGroupService;
    private final GroupsFactory groupsFactory;

    @Override
    public List<SharedGroup> findAllByUserId(Long userId) {
        return sharedGroupRepository.findAllByUserSharedId(userId);
    }

    @Override
    public List<GroupsDto> getAllWithShared(Long userId) {
        var ret = new ArrayList<GroupsDto>();
        ret.addAll(projectGroupService.getAllByProjectUserId(userId).stream()
                .map(group -> groupsFactory.to(group, true))
                .toList());
        ret.addAll(findAllByUserId(userId).stream()
                .map(sharedGroup -> groupsFactory.to(sharedGroup.getGroupShared(), false))
                .toList());
        return ret;
    }

    @Override
    public void shareGroup(ShareGroupDto shareGroupDto) {
        var userSharing = projectUserService.getProjectUserById(shareGroupDto.getUserSharing());
        var groupShared = projectGroupService.getById(shareGroupDto.getGroupId());
        var userSharedTo = projectUserService.getProjectUserByEmail(shareGroupDto.getUserSharedToEmail());
        sharedGroupRepository.save(SharedGroup.builder()
                .groupShared(groupShared)
                .userShared(userSharedTo)
                .userSharing(userSharing)
                .build());
    }
}
