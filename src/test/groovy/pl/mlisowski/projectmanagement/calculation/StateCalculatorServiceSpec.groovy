package pl.mlisowski.projectmanagement.calculation

import pl.mlisowski.projectmanagement.project.application.ProjectService
import pl.mlisowski.projectmanagement.project.domain.Project
import pl.mlisowski.projectmanagement.project.domain.ProjectStatus
import pl.mlisowski.projectmanagement.state.application.ProjectStateService
import pl.mlisowski.projectmanagement.state.domain.ProjectState
import pl.mlisowski.projectmanagement.task.domain.Task
import spock.lang.Specification
import spock.lang.Subject

class StateCalculatorServiceSpec extends Specification {

    ProjectStateService projectStateService = Mock()

    ProjectService projectService = Mock()

    @Subject
    StateCalculatorService stateCalculatorService = new StateCalculatorService(projectStateService, projectService)

    def "Should calculate state corectly"() {
        given:
        def project = Project.builder()
                .status(ProjectStatus.IN_PROGRESS)
                .states([
                        ProjectState.builder()
                                .tasks([
                                        Task.builder()
                                                .finished(true)
                                                .build(),
                                        Task.builder()
                                                .finished(true)
                                                .build()
                                ]).build()
                ] as Set)
                .build()
        when:
        stateCalculatorService.calculateCurrentStates(project)
        then:
        1 * projectStateService.updateProjectState(_)
        1 * projectService.updateProject(_)
        ProjectStatus.FINISHED == project.getStatus()
    }

}
