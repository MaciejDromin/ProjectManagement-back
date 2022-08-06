package pl.mlisowski.projectmanagement.state

import pl.mlisowski.projectmanagement.group.domain.ProjectGroupFactory
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupState
import pl.mlisowski.projectmanagement.state.domain.factory.PredefinedGroupStateFactory
import pl.mlisowski.projectmanagement.state.domain.dto.PredefinedGroupStateDto
import pl.mlisowski.projectmanagement.task.domain.TaskFactory
import spock.lang.Specification
import spock.lang.Subject

class PredefinedGroupStateFactorySpec extends Specification {

    TaskFactory taskFactory = Mock()

    @Subject
    PredefinedGroupStateFactory predefinedGroupStateFactory = new PredefinedGroupStateFactory(taskFactory)

    def "Should create PredefinedGroupState"() {
        given:
        def predefinedGroupStateDto = PredefinedGroupStateDto.builder()
                .name("test")
                .tasks([])
                .build()
        when:
        def predefinedGroupState = predefinedGroupStateFactory.from(predefinedGroupStateDto)
        then:
        with(predefinedGroupState) {
            name == "test"
            tasks.isEmpty()
        }
    }

    def "Should create PredefinedGroupStateDto"() {
        given:
        def predefinedGroupState = PredefinedGroupState.builder()
                .name("test")
                .tasks([])
                .build()
        when:
        def predefinedGroupStateDto = predefinedGroupStateFactory.to(predefinedGroupState)
        then:
        with(predefinedGroupStateDto) {
            name == "test"
            tasks.isEmpty()
        }
    }

}
