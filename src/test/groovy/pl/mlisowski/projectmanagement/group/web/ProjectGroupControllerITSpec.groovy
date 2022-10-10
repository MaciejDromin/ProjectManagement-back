package pl.mlisowski.projectmanagement.group.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import pl.mlisowski.projectmanagement.BaseITSpec
import pl.mlisowski.projectmanagement.administration.application.port.ProjectUserRepository
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser
import pl.mlisowski.projectmanagement.group.application.port.ProjectGroupRepository
import pl.mlisowski.projectmanagement.group.application.port.SharedGroupRepository
import pl.mlisowski.projectmanagement.group.domain.ProjectGroup
import pl.mlisowski.projectmanagement.group.domain.SharedGroup
import pl.mlisowski.projectmanagement.group.domain.dto.ProjectGroupDto
import pl.mlisowski.projectmanagement.group.domain.dto.ShareGroupDto

@AutoConfigureMockMvc(addFilters = false)
class ProjectGroupControllerITSpec extends BaseITSpec {

    @Autowired
    ProjectUserRepository projectUserRepository

    @Autowired
    ProjectGroupRepository projectGroupRepository

    @Autowired
    SharedGroupRepository sharedGroupRepository

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    def "should properly get all groups" () {
        given:
        def user2 = projectUserRepository.save(ProjectUser.builder()
                .username("test8")
                .email("test8@test.com")
                .password("password")
                .build())
        def group = projectGroupRepository.save(ProjectGroup.builder()
                .projectUser(user2)
                .name("test")
                .build())
        when:
        def result = mockMvc.perform(
                MockMvcRequestBuilders.get("/groups"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        result.contains(group.getName())
    }

    def "should properly get all groups with shared" () {
        def user = projectUserRepository.save(ProjectUser.builder()
                .username("test9")
                .email("test9@test.com")
                .password("password")
                .build())
        def user2 = projectUserRepository.save(ProjectUser.builder()
                .username("test10")
                .email("test10@test.com")
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
        def result = mockMvc.perform(
                MockMvcRequestBuilders.get("/groups/users/${user2.getId()}/shared"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        result.contains(group.getName())
    }

    def "should properly share groups" () {
        given:
        def user = projectUserRepository.save(ProjectUser.builder()
                .username("test12")
                .email("test12@test.com")
                .password("password")
                .build())
        def user2 = projectUserRepository.save(ProjectUser.builder()
                .username("test13")
                .email("test13@test.com")
                .password("password")
                .build())
        def group = projectGroupRepository.save(ProjectGroup.builder()
                .projectUser(user)
                .name("test")
                .build())
        def shareGroupDto = ShareGroupDto.builder()
                .userSharedToEmail(user2.getEmail())
                .groupId(group.getId())
                .userSharing(user.getId())
                .build()
        when:
        mockMvc.perform(
                MockMvcRequestBuilders.post("/groups/share")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shareGroupDto)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        def result = sharedGroupRepository.findAllByUserSharedId(user2.getId())
        then:
        with (result) {
            size() == 1
            with (first()) {
                groupShared.name == "test"
            }
        }
    }

    def "should properly get groups per user" () {
        given:
        def user2 = projectUserRepository.save(ProjectUser.builder()
                .username("test11")
                .email("test11@test.com")
                .password("password")
                .build())
        def group = projectGroupRepository.save(ProjectGroup.builder()
                .projectUser(user2)
                .name("test")
                .build())
        when:
        def result = mockMvc.perform(
                MockMvcRequestBuilders.get("/groups/users/${user2.getId()}"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        result.contains(group.getName())
    }

    def "should properly add group for user" () {
        given:
        def user = projectUserRepository.save(ProjectUser.builder()
                .username("test14")
                .email("test14@test.com")
                .password("password")
                .build())
        def projectGroupDto = ProjectGroupDto.builder()
                .name("test")
                .build()
        when:
        def result = mockMvc.perform(
                MockMvcRequestBuilders.post("/groups/users/${user.getId()}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectGroupDto)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        result.contains(projectGroupDto.getName())
    }

}
