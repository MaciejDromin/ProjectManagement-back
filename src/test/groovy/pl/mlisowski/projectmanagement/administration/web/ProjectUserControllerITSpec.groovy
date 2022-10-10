package pl.mlisowski.projectmanagement.administration.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import pl.mlisowski.projectmanagement.BaseITSpec
import pl.mlisowski.projectmanagement.administration.application.port.ProjectUserRepository
import pl.mlisowski.projectmanagement.administration.application.port.RecoveryTokenRepository
import pl.mlisowski.projectmanagement.administration.application.port.VerificationTokenRepository
import pl.mlisowski.projectmanagement.administration.domain.EmailDto
import pl.mlisowski.projectmanagement.administration.domain.ProjectUser
import pl.mlisowski.projectmanagement.administration.domain.dto.RecoveryCredentialsDto
import pl.mlisowski.projectmanagement.administration.domain.dto.RegisterCredentialsDto
import pl.mlisowski.projectmanagement.administration.domain.dto.UserDetailsDto
import pl.mlisowski.projectmanagement.administration.domain.mail.MailSender

@AutoConfigureMockMvc(addFilters = false)
class ProjectUserControllerITSpec extends BaseITSpec {

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    MockMvc mockMvc

    @Autowired
    VerificationTokenRepository verificationTokenRepository

    @Autowired
    RecoveryTokenRepository recoveryTokenRepository

    @Autowired
    ProjectUserRepository projectUserRepository

    @SpringBean
    MailSender mailSender = Mock()

    def "should properly get User Details" () {
        given:
        def user = projectUserRepository.save(ProjectUser.builder()
                .username("userDetails")
                .email("userDetails@test.com")
                .build())
        SecurityContextHolder contextHolder = Mock()
        def authentication = Mock(Authentication)
        authentication.getName() >> "userDetails"
        SecurityContextHolder.getContext().setAuthentication(authentication)
        when:
        def result = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/me"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn()
                .response
                .contentAsString, UserDetailsDto.class)
        then:
        with (result) {
            id == user.getId()
            name == user.getUsername()
            email == user.getEmail()
        }
    }

    def "should properly register user" () {
        given:
        def registerCredentials = RegisterCredentialsDto.builder()
                .username("registerTest")
                .email("registerTest@test.com")
                .password("test")
                .build()
        mailSender.sendMail(_ as String, _ as String, _ as String) >> {}
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerCredentials)))
                .andExpect(MockMvcResultMatchers.status().is(201))
        def user = projectUserRepository.findProjectUserByUsername("registerTest").get()
        !user.isEnabled()
        def token = verificationTokenRepository.findByProjectUser(user).get()
        mockMvc.perform(MockMvcRequestBuilders.get("/registration-confirm")
                .param("token", token.getToken()))
                .andExpect(MockMvcResultMatchers.status().is(200))
        and:
        user = projectUserRepository.findProjectUserByUsername("registerTest").get()
        then:
        user.isEnabled()
    }

    def "should throw exception on user registration" () {
        given:
        def user = projectUserRepository.save(ProjectUser.builder()
                .username("userExists")
                .email("userExists@test.com")
                .build())
        def registerCredentials = RegisterCredentialsDto.builder()
                .username("userExists")
                .email("userExists@test.com")
                .password("test")
                .build()
        mailSender.sendMail(_ as String, _ as String, _ as String) >> {}
        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerCredentials)))
                .andExpect(MockMvcResultMatchers.status().is(500))
                .andReturn()
                .response
                .contentAsString
        then:
        result.contains("User with such credentials already exists!")
    }

    def "should properly recover password" () {
        given:
        projectUserRepository.save(ProjectUser.builder()
                .username("Username")
                .email("Email@test.com")
                .password("password")
                .build())
        def email = EmailDto.builder()
                .email("Email@test.com")
                .build()
        mailSender.sendMail(_ as String, _ as String, _ as String) >> {}
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/password-recovery")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(email)))
                .andExpect(MockMvcResultMatchers.status().is(202))
        var user = projectUserRepository.findProjectUserByUsername("Username").get()
        user.getPassword() == "password"
        def token = recoveryTokenRepository.findByProjectUser(user).get()
        def recoveryCredentials = RecoveryCredentialsDto.builder()
                .password("test")
                .token(token.getToken())
                .build()
        mockMvc.perform(MockMvcRequestBuilders.post("/recovery-confirm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recoveryCredentials)))
                .andExpect(MockMvcResultMatchers.status().is(200))
        and:
        user = projectUserRepository.findProjectUserByUsername("Username").get()
        then:
        user.getPassword() != "password"
    }

    def "should throw exception on user not found" () {
        given:
        def email = EmailDto.builder()
                .email("EmailNotFound@test.com")
                .build()
        when:
        def result = mockMvc.perform(MockMvcRequestBuilders.post("/password-recovery")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(email)))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andReturn()
                .response
                .contentAsString
        then:
        result.contains("User with email %s doesn't exists!".formatted(email.getEmail()))
    }

}
