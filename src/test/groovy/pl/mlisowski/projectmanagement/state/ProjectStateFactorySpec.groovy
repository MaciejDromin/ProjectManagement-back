package pl.mlisowski.projectmanagement.state

import pl.mlisowski.projectmanagement.project.domain.ProjectFactory
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto
import pl.mlisowski.projectmanagement.state.domain.ProjectState
import pl.mlisowski.projectmanagement.state.domain.ProjectStateFactory
import pl.mlisowski.projectmanagement.state.domain.dto.ProjectStateDto
import pl.mlisowski.projectmanagement.task.domain.TaskFactory
import spock.lang.Specification
import spock.lang.Subject

class ProjectStateFactorySpec extends Specification {

    TaskFactory taskFactory = Mock()
    ProjectFactory projectFactory = Mock()

    @Subject
    ProjectStateFactory projectStateFactory = new ProjectStateFactory()

    def setup() {
        projectStateFactory.setTaskFactory(taskFactory)
        projectStateFactory.setProjectFactory(projectFactory)
    }

    def "Should create ProjectState"() {
        given:
        def projectStateDto = ProjectStateDto.builder()
                .name("test")
                .completed(false)
                .tasks([])
                .build()
        when:
        def projectState = projectStateFactory.from(projectStateDto)
        then:
        1 * projectFactory.from(_) >> null
        with(projectState){
            name == "test"
            completed == false
            tasks.size() == 0
        }
    }

    def "Should create ProjectStateDto"() {
        given:
        def projectState = ProjectState.builder()
                .name("test")
                .completed(false)
                .tasks([])
                .build()
        when:
        def projectStateDto = projectStateFactory.to(projectState)
        then:
        1 * projectFactory.to(_) >> null
        with(projectStateDto){
            name == "test"
            completed == false
            tasks.isEmpty()
        }
    }

}
