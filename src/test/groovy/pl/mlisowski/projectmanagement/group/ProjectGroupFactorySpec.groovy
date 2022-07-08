package pl.mlisowski.projectmanagement.group

import pl.mlisowski.projectmanagement.group.domain.ProjectGroup
import pl.mlisowski.projectmanagement.group.domain.ProjectGroupFactory
import pl.mlisowski.projectmanagement.group.domain.dto.ProjectGroupDto
import pl.mlisowski.projectmanagement.project.domain.ProjectFactory
import pl.mlisowski.projectmanagement.state.domain.PredefinedGroupStateFactory
import spock.lang.Specification
import spock.lang.Subject

class ProjectGroupFactorySpec extends Specification {

    ProjectFactory projectFactory = Mock()
    PredefinedGroupStateFactory predefinedGroupStateFactory = Mock()

    @Subject
    ProjectGroupFactory projectGroupFactory = new ProjectGroupFactory()

    def setup() {
        projectGroupFactory.setProjectFactory(projectFactory)
        projectGroupFactory.setPredefinedGroupStateFactory(predefinedGroupStateFactory)
    }

    def "Should create ProjectGroup"() {
        given:
        def projectGroupDto = ProjectGroupDto.builder()
                .name("test")
                .projects([] as Set)
                .predefinedGroupStates([] as Set)
                .build()
        when:
        def projectGroup = projectGroupFactory.from(projectGroupDto)
        then:
        with(projectGroup) {
            name == "test"
            projects.isEmpty()
            predefinedGroupStates.isEmpty()
        }
    }

    def "Should create ProjectGroupDto"() {
        given:
        def projectGroup = ProjectGroup.builder()
                .name("test")
                .projects([] as Set)
                .predefinedGroupStates([] as Set)
                .build()
        when:
        def projectGroupDto = projectGroupFactory.to(projectGroup)
        then:
        with(projectGroupDto) {
            name == "test"
            projects.isEmpty()
            predefinedGroupStates.isEmpty()
        }
    }

}
