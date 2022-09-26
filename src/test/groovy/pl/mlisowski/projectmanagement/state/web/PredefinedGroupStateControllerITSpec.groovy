package pl.mlisowski.projectmanagement.state.web

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
import pl.mlisowski.projectmanagement.group.domain.ProjectGroup
import pl.mlisowski.projectmanagement.state.application.port.PredefinedGroupStateRepository
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto

@AutoConfigureMockMvc(addFilters = false)
class PredefinedGroupStateControllerITSpec extends BaseITSpec {

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    MockMvc mockMvc

    @Autowired
    PredefinedGroupStateRepository predefinedGroupStateRepository

    @Autowired
    ProjectGroupRepository projectGroupRepository

    @Autowired
    ProjectUserRepository projectUserRepository

    def "Should properly get all predefined group states" () {
        given:
        def predefinedGroupState = predefinedGroupStateRepository.save(PredefinedGroupState.builder()
                .name("test")
                .build())

        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.get("/predefinedgroupstates"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        response.contains(predefinedGroupState.getName())
    }

    def "Should properly save predefined group state" () {
        given:
        def predefinedGroupState = PredefinedGroupStateDto.builder()
                .name("test")
                .tasks([])
                .build()

        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.post("/predefinedgroupstates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(predefinedGroupState)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        response.contains(predefinedGroupState.getName())
    }

    def "Should properly save predefined group state in Group" () {
        given:
        def user = projectUserRepository.save(ProjectUser.builder()
                .username("name")
                .build())
        def group = projectGroupRepository.save(ProjectGroup.builder()
                .name("predefinedGroupState")
                .projectUser(user)
                .build())
        def predefinedGroupState = PredefinedGroupStateDto.builder()
                .name("test")
                .tasks([])
                .build()

        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.post("/predefinedgroupstates/groups/${group.getId()}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(predefinedGroupState)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        response.contains(predefinedGroupState.getName())
    }

}
