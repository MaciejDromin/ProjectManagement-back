package pl.mlisowski.projectManagement.calculation

import pl.mlisowski.projectManagement.hours.application.HoursService
import pl.mlisowski.projectManagement.hours.domain.Hours
import spock.lang.Specification
import spock.lang.Subject

class CalculationServiceSpec extends Specification {

    HoursService hoursService = Mock()

    @Subject
    CalculationService calculationService = new CalculationService(hoursService)

    def "Should properly calculate Real Hours from top"() {
        given:
        def hoursTop = Hours.builder()
                .childHours([
                        Hours.builder()
                                .realHours(20)
                                .build(),
                        Hours.builder()
                                .realHours(15)
                                .build()
                ])
                .build()
        when:
        calculationService.calculateRealHours(hoursTop)
        then:
        1 * hoursService.saveHours(_ as Hours)
        hoursTop.getRealHours() == 35
    }

    def "Should properly calculate Real Hours from anywhere"() {
        given:
        def ch = Hours.builder()
                .realHours(0)
                .childHours([
                        Hours.builder()
                                .realHours(20)
                                .build(),
                        Hours.builder()
                                .realHours(15)
                                .build()
                ])
                .build()
        def hoursTop = Hours.builder()
                .childHours([
                        Hours.builder()
                                .realHours(20)
                                .build(),
                        Hours.builder()
                                .realHours(15)
                                .build(),
                        ch
                ])
                .build()
        ch.setParentHours(hoursTop)
        when:
        calculationService.calculateRealHours(ch)
        then:
        2 * hoursService.saveHours(_ as Hours)
        hoursTop.getRealHours() == 70
    }

    def "Should properly calculate Estimated Hours from top"() {
        given:
        def hoursTop = Hours.builder()
                .childHours([
                        Hours.builder()
                                .estimatedHours(20)
                                .build(),
                        Hours.builder()
                                .estimatedHours(15)
                                .build()
                ])
                .build()
        when:
        calculationService.calculateEstimatedHours(hoursTop)
        then:
        1 * hoursService.saveHours(_ as Hours)
        hoursTop.getEstimatedHours() == 35
    }

    def "Should properly calculate Estimated Hours from anywhere"() {
        given:
        def ch = Hours.builder()
                .childHours([
                        Hours.builder()
                                .estimatedHours(20)
                                .build(),
                        Hours.builder()
                                .estimatedHours(15)
                                .build()
                ])
                .build()
        def hoursTop = Hours.builder()
                .childHours([
                        Hours.builder()
                                .estimatedHours(20)
                                .build(),
                        Hours.builder()
                                .estimatedHours(15)
                                .build(),
                        ch
                ])
                .build()
        ch.setParentHours(hoursTop)
        when:
        calculationService.calculateEstimatedHours(ch)
        then:
        2 * hoursService.saveHours(_ as Hours)
        hoursTop.getEstimatedHours() == 70
    }

}
