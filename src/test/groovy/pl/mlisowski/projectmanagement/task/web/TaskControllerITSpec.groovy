package pl.mlisowski.projectmanagement.task.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import pl.mlisowski.projectmanagement.BaseITSpec
import pl.mlisowski.projectmanagement.hours.application.port.HoursRepository
import pl.mlisowski.projectmanagement.state.application.port.PredefinedGroupStateRepository
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState
import pl.mlisowski.projectmanagement.task.application.port.TaskRepository
import pl.mlisowski.projectmanagement.task.domain.Task
import pl.mlisowski.projectmanagement.task.domain.dto.TaskDto
import pl.mlisowski.projectmanagement.task.domain.dto.TaskWithHoursDto

@AutoConfigureMockMvc(addFilters = false)
class TaskControllerITSpec extends BaseITSpec {

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    MockMvc mockMvc

    @Autowired
    TaskRepository taskRepository

    @Autowired
    HoursRepository hoursRepository

    @Autowired
    PredefinedGroupStateRepository predefinedGroupStateRepository

    def "Should properly get all tasks" () {
        given:
        def task = taskRepository.save(Task.builder()
                .name("test")
                .build())

        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.get("/tasks"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        response.contains(task.getName())
    }

    def "Should properly save task" () {
        given:
        def task = TaskDto.builder()
                .name("test")
                .build()

        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        response.contains(task.getName())
    }

    def "Should properly save task in PredefinedGroupState" () {
        given:
        def predefinedGroupState = predefinedGroupStateRepository.save(PredefinedGroupState.builder()
                .name("predefinedGroupState")
                .build())
        def task = TaskDto.builder()
                .name("test")
                .build()

        when:
        def response = mockMvc.perform(
                MockMvcRequestBuilders.post("/tasks/predefinedgroupstates/${predefinedGroupState.getId()}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString
        then:
        response.contains(task.getName())
    }

    def "Should properly save task with Hours" () {
        given:
        def task = TaskDto.builder()
                .name("test")
                .build()
        def taskWithHours = TaskWithHoursDto.builder()
                .taskDto(task)
                .realHours(0)
                .estimatedHours(5)
                .build()
        when:
        def response = objectMapper.readValue(mockMvc.perform(
                MockMvcRequestBuilders.post("/tasks/savewithhours")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskWithHours)))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString, TaskDto.class)
        def hours = hoursRepository.findHoursByOwnerId(response.getUuid())
        then:
        response.getName() == "test"
        with (hours) {
            realHours == 0
            estimatedHours == 5
        }
    }

}
