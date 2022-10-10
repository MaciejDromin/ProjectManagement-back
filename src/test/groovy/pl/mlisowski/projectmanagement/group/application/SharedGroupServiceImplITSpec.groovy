package pl.mlisowski.projectmanagement.group.application

import org.springframework.beans.factory.annotation.Autowired
import pl.mlisowski.projectmanagement.BaseITSpec
import pl.mlisowski.projectmanagement.administration.application.port.ProjectUserRepository
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser
import pl.mlisowski.projectmanagement.group.application.port.ProjectGroupRepository
import pl.mlisowski.projectmanagement.group.application.port.SharedGroupRepository
import pl.mlisowski.projectmanagement.group.domain.ProjectGroup
import pl.mlisowski.projectmanagement.group.domain.SharedGroup
import pl.mlisowski.projectmanagement.group.domain.dto.ShareGroupDto

import javax.transaction.Transactional

class SharedGroupServiceImplITSpec extends BaseITSpec {

    @Autowired
    ProjectGroupRepository projectGroupRepository

    @Autowired
    SharedGroupRepository sharedGroupRepository

    @Autowired
    ProjectUserRepository projectUserRepository

    @Autowired
    SharedGroupService sharedGroupService

    def "should properly get all shared groups for user"() {
        given:
        def user = projectUserRepository.save(ProjectUser.builder()
                .username("test3")
                .email("test3@test.com")
                .password("password")
                .build())
        def user2 = projectUserRepository.save(ProjectUser.builder()
                .username("test4")
                .email("test4@test.com")
                .password("password")
                .build())
        def group = projectGroupRepository.save(ProjectGroup.builder()
                .projectUser(user)
                .name("test")
                .build())
        def sharedGroup = sharedGroupRepository.save(SharedGroup.builder()
                .groupShared(group)
                .userShared(user2)
                .userSharing(user)
                .build())
        when:
        def result = sharedGroupService.findAllByUserId(user2.getId())
        then:
        result.contains(sharedGroup)
    }

    @Transactional
    def "should get all groups for userId" () {
        given:
        def user = projectUserRepository.save(ProjectUser.builder()
                .username("test5")
                .email("test5@test.com")
                .password("password")
                .build())
        def user2 = projectUserRepository.save(ProjectUser.builder()
                .username("test6")
                .email("test6@test.com")
                .password("password")
                .build())
        def group = projectGroupRepository.save(ProjectGroup.builder()
                .projectUser(user)
                .name("test")
                .build())
        projectGroupRepository.save(ProjectGroup.builder()
                .projectUser(user)
                .name("test2")
                .build())
        projectGroupRepository.save(ProjectGroup.builder()
                .projectUser(user)
                .name("test3")
                .build())
        projectGroupRepository.save(ProjectGroup.builder()
                .projectUser(user2)
                .name("testing")
                .build())

        def sharedGroup = sharedGroupRepository.save(SharedGroup.builder()
                .groupShared(group)
                .userShared(user2)
                .userSharing(user)
                .build())
        when:
        def result = sharedGroupService.getAllWithShared(user2.getId())
        then:
        with (result) {
            size() == 2
            with (first()) {
                name == "testing"
            }
            with (last()) {
                name == "test"
            }
        }
    }

    @Transactional
    def "should properly share groups between users" () {
        given:
        def user = projectUserRepository.save(ProjectUser.builder()
                .username("test7")
                .email("test7@test.com")
                .password("password")
                .build())
        def user2 = projectUserRepository.save(ProjectUser.builder()
                .username("test8")
                .email("test8@test.com")
                .password("password")
                .build())
        def group = projectGroupRepository.save(ProjectGroup.builder()
                .projectUser(user)
                .name("test")
                .build())
        when:
        sharedGroupService.shareGroup(ShareGroupDto.builder()
                .userSharing(user.getId())
                .groupId(group.getId())
                .userSharedToEmail(user2.getEmail())
                .build())
        def result = sharedGroupRepository.findAllByUserSharedId(user2.getId())
        then:
        with (result) {
            size() == 1
            with (first()) {
                groupShared == group
            }
        }
    }

}
