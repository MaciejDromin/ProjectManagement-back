package pl.mlisowski.projectmanagement.task

import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupStateFactory
import pl.mlisowski.projectmanagement.state.domain.ProjectState
import pl.mlisowski.projectmanagement.state.domain.ProjectStateFactory
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto
import pl.mlisowski.projectmanagement.state.domain.dto.ProjectStateDto
import pl.mlisowski.projectmanagement.task.domain.Task
import pl.mlisowski.projectmanagement.task.domain.TaskFactory
import pl.mlisowski.projectmanagement.task.domain.dto.TaskDto
import spock.lang.Specification
import spock.lang.Subject

class TaskFactorySpec extends Specification {

    PredefinedGroupStateFactory predefinedGroupStateFactory = Mock()
    ProjectStateFactory projectStateFactory = Mock()

    @Subject
    TaskFactory taskFactory = new TaskFactory(projectStateFactory)

    def setup() {
        taskFactory.setPredefinedGroupStateFactory(predefinedGroupStateFactory)
    }

    def "Should create Task"() {
        given:
        def taskDto = TaskDto.builder()
                .name("test")
                .finished(false)
                .build()
        when:
        def task = taskFactory.from(taskDto)
        then:
        predefinedGroupStateFactory.from(_ as PredefinedGroupStateDto) >> null
        projectStateFactory.from(_ as ProjectStateDto) >> null
        with(task) {
            name == "test"
            finished == false
        }
    }

    def "Should create TaskDto"() {
        given:
        def task = Task.builder()
                .name("test")
                .finished(false)
                .build()
        when:
        def taskDto = taskFactory.to(task)
        then:
        predefinedGroupStateFactory.to(_ as PredefinedGroupState) >> null
        projectStateFactory.to(_ as ProjectState) >> null
        with(taskDto) {
            name == "test"
            finished == false
        }
    }

}
