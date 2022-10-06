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
import pl.mlisowski.projectmanagement.project.domain.NestProjectDto
import pl.mlisowski.projectmanagement.project.domain.Project
import pl.mlisowski.projectmanagement.project.domain.ProjectStatus
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectCreationDto
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto
import pl.mlisowski.projectmanagement.project.domain.factory.ProjectFactory

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

    @Autowired
    ProjectFactory projectFactory

    def "should properly get all projects" () {
        given:
        def project = projectRepository.save(Project.builder()
                .name("testGetAllProjects")
                .build())
        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/projects"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        result.contains(project.getName())
    }

    def "should properly get all projects in group" () {
        given:
        def user = projectUserRepository.save(ProjectUser.builder()
                .username("test")
                .build())
        def group = projectGroupRepository.save(ProjectGroup.builder()
                .id(1)
                .projectUser(user)
                .build())
        def project = projectRepository.save(Project.builder()
                .name("testGetAllProjectsInGroup")
                .group(group)
                .build())
        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/projects/groups/${group.getId()}"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        result.contains(project.getName())
    }

    def "should get project by ID" () {
        given:
        def project = projectRepository.save(Project.builder()
                .name("testGetProjectById")
                .build())
        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.get("/projects/${project.getId()}"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        result.contains(project.getName())
    }

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
        def response = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/projects")
                .content(objectMapper.writeValueAsString(projectCreation))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andReturn()
                .response
                .contentAsString, ProjectDto.class)
        then:
        with (response) {
            name == "test"
            status == ProjectStatus.PLANNING
        }
        def hours = hoursRepository.findHoursByOwnerId(response.getUuid())
        with(hours) {
            estimatedHours == 0
            realHours == 0
        }
    }

    def "should properly nest projects" () {
        given:
        def project = projectRepository.save(Project.builder()
                .name("testNestTo")
                .build())
        def project2 = projectRepository.save(Project.builder()
                .name("testNest")
                .build())
        def nestProjectRequest = NestProjectDto.builder()
                .nestTo(projectFactory.to(project))
                .nested(projectFactory.to(project2))
                .build()
        when:
        def result = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/projects/nestproject")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nestProjectRequest)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString, ProjectDto.class)
        then:
        with (result) {
            name == project.getName()
            status == ProjectStatus.PLANNING
            with (childProjects) {
                size() == 1
                with (first()) {
                    name == project2.getName()
                    status == ProjectStatus.PLANNING
                }
            }
        }
    }

    def "should properly delete project" () {
        given:
        def project = projectRepository.save(Project.builder()
                .name("testForDelete")
                .build())
        when:
        mockMvc.perform(MockMvcRequestBuilders.delete("/projects/${project.getId()}"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        def projects = projectRepository.findAll()
        then:
        !projects.contains(project)
    }

}
