package pl.mlisowski.projectmanagement

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ProjectManagementApplicationSpec extends Specification {

    @Autowired
    ProjectManagementApplication projectManagementApplication

    def "context should load"() {
        expect:
        projectManagementApplication
    }

}
