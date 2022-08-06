package pl.mlisowski.projectmanagement.project

import pl.mlisowski.projectmanagement.group.domain.ProjectGroupFactory
import pl.mlisowski.projectmanagement.project.domain.Project
import pl.mlisowski.projectmanagement.project.domain.factory.ProjectFactory
import pl.mlisowski.projectmanagement.project.domain.ProjectStatus
import pl.mlisowski.projectmanagement.project.domain.dto.ProjectDto
import pl.mlisowski.projectmanagement.state.domain.factory.ProjectStateFactory
import spock.lang.Specification
import spock.lang.Subject

class ProjectFactorySpec extends Specification {

    ProjectStateFactory projectStateFactory = Mock()

    @Subject
    ProjectFactory projectFactory = new ProjectFactory(projectStateFactory)

    def "Should create Project"() {
        given:
        def projectDto = ProjectDto.builder()
                .name("test")
                .status(ProjectStatus.IN_PROGRESS)
                .childProjects([] as Set)
                .states([] as Set)
                .build()
        when:
        def project = projectFactory.from(projectDto)
        then:
        with(project) {
            name == "test"
            status == ProjectStatus.IN_PROGRESS
            childProjects.isEmpty()
            states.isEmpty()
        }
    }

    def "Should create ProjectDto"() {
        given:
        def project = Project.builder()
                .name("test")
                .status(ProjectStatus.IN_PROGRESS)
                .childProjects([] as Set)
                .states([] as Set)
                .build()
        when:
        def projectDto = projectFactory.to(project)
        then:
        with(projectDto) {
            name == "test"
            status == ProjectStatus.IN_PROGRESS
            childProjects.isEmpty()
            states.isEmpty()
        }
    }

}
