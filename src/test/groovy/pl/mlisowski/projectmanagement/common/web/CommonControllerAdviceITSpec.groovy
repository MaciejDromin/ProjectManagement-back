package pl.mlisowski.projectmanagement.common.web

import com.auth0.jwt.exceptions.TokenExpiredException
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import pl.mlisowski.projectmanagement.BaseITSpec
import pl.mlisowski.projectmanagement.project.application.ProjectService
import spock.lang.Unroll

import javax.persistence.EntityNotFoundException
import java.time.LocalDateTime
import java.time.ZoneOffset

@AutoConfigureMockMvc(addFilters = false)
class CommonControllerAdviceITSpec extends BaseITSpec {

    static final String ERROR_MESSAGE = "default error message"

    @SpringBean
    ProjectService projectService = Mock()

    @Autowired
    MockMvc mockMvc

    @Unroll
    def "Should properly handle thrown exceptions"() {
        given:
        projectService.getAll() >> { throw thrownException }
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/projects"))
                .andExpect(MockMvcResultMatchers.status().is(expectedStatus))
                .andReturn()
                .response
                .contentAsString
        then:
        response.contains(expectedMessage)
        where:
        thrownException                                                                           | expectedStatus   | expectedMessage
        new TokenExpiredException(ERROR_MESSAGE, LocalDateTime.now().toInstant(ZoneOffset.UTC))   | 401              | ERROR_MESSAGE
        new EntityNotFoundException(ERROR_MESSAGE)                                                | 404              | ERROR_MESSAGE
        new Exception(ERROR_MESSAGE)                                                              | 500              | "500"
    }

}
