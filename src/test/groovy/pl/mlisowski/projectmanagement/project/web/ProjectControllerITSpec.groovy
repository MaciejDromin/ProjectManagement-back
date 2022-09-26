package pl.mlisowski.projectmanagement.project.web

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
import pl.mlisowski.projectmanagement.hours.application.port.HoursRepository
import pl.mlisowski.projectmanagement.project.application.port.ProjectRepository
import pl.mlisowski.projectmanagement.project.domain.ProjectStatus
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectCreationDto
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto

@AutoConfigureMockMvc(addFilters = false)
class ProjectControllerITSpec extends BaseITSpec {

    @Autowired
    ProjectRepository projectRepository

    @Autowired
    ProjectGroupRepository projectGroupRepository

    @Autowired
    ProjectUserRepository projectUserRepository

    @Autowired
    HoursRepository hoursRepository

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    MockMvc mockMvc

    def "Should create Project with Hours object"() {
        given:
        def user = projectUserRepository.save(ProjectUser.builder()
                .username("test")
                .build())
        projectGroupRepository.save(ProjectGroup.builder()
                .id(1)
                .projectUser(user)
                .build())

        def projectCreation = ProjectCreationDto.builder()
                .name("test")
                .description("test description")
                .groupId(1)
                .build()
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post("/projects")
                .content(objectMapper.writeValueAsString(projectCreation))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(201))
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
            status == ProjectStatus.PLANNING
        }
        def hours = hoursRepository.findHoursByOwnerId(projectSaved.getId())
        with(hours) {
            estimatedHours == 0
            realHours == 0
        }
    }

}
