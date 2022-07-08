package pl.mlisowski.projectmanagement.project.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import pl.mlisowski.projectmanagement.BaseITSpec
import pl.mlisowski.projectmanagement.hours.application.port.HoursRepository
import pl.mlisowski.projectmanagement.hours.domain.Hours
import pl.mlisowski.projectmanagement.project.application.port.ProjectRepository
import pl.mlisowski.projectmanagement.project.domain.ProjectStatus
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto

@AutoConfigureMockMvc
class ProjectControllerITSpec extends BaseITSpec {

    @Autowired
    ProjectRepository projectRepository

    @Autowired
    HoursRepository hoursRepository

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    MockMvc mockMvc

    def "Should create Project with Hours object"() {
        given:
        def projectDto = ProjectDto.builder()
                .name("test")
                .status(ProjectStatus.DESIGN)
                .childProjects([] as Set)
                .states([] as Set)
                .build()
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/project")
                .content(objectMapper.writeValueAsString(projectDto))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        response.contains("test")
        def projects = projectRepository.findAll()
        projects.size() == 1
        def projectSaved = projects.get(0)
        with(projectSaved) {
            name == "test"
            status == ProjectStatus.DESIGN
        }
        def hours = hoursRepository.findHoursByOwnerId(projectSaved.getId())
        with(hours) {
            estimatedHours == 0
            realHours == 0
        }
    }

}
