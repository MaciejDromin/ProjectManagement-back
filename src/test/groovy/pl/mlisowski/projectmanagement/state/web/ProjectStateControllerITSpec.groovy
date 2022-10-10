package pl.mlisowski.projectmanagement.state.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import pl.mlisowski.projectmanagement.BaseITSpec
import pl.mlisowski.projectmanagement.state.application.port.ProjectStateRepository
import pl.mlisowski.projectmanagement.state.domain.ProjectState
import pl.mlisowski.projectmanagement.state.domain.dto.ProjectStateDto

@AutoConfigureMockMvc(addFilters = false)
class ProjectStateControllerITSpec extends BaseITSpec {

    @Autowired
    ProjectStateRepository projectStateRepository

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    def "should properly get all project states" () {
        given:
        def projectState = projectStateRepository.save(ProjectState.builder()
                .name("projectState")
                .build())
        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/projectstates"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        result.contains(projectState.getName())
    }

    def "should properly save project state" () {
        given:
        def projectState = ProjectStateDto.builder()
                .name("projectState")
                .tasks([])
                .build()
        when:
        def result = mockMvc.perform(
                MockMvcRequestBuilders.post("/projectstates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectState)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        result.contains(projectState.getName())
    }

}
